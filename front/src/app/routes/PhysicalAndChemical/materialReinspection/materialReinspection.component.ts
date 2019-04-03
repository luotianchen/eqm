import {Component, OnInit, TemplateRef} from '@angular/core';
import {MaterialReinspectionService} from "./materialReinspection.service";
import {FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {Observable, Observer} from "rxjs/index";

@Component({
  selector: 'app-materialReinspection',
  templateUrl: 'materialReinspection.component.html',
  styleUrls: ['./materialReinspection.component.less'],
  providers: [MaterialReinspectionService]
})
export class  MaterialReinspectionComponent implements OnInit {
  public codedmarkings;
  validateForm: FormGroup;

  public direct = [ //直接判断是否为null来控制是否现实的
    "impacttemp",
    "bendaxdia",
  ];
  public deviation = {
    "c":[],
    "si":[],
    "mn":[],
    "cu":[],
    "ni":[],
    "cr":[],
    "mo":[],
    "nb":[],
    "v":[],
    "ti":[],
    "als":[],
    "alt":[],
    "n":[],
    "fe":[],
    "mg":[],
    "zn":[],
    "b":[],
    "w":[],
    "sb":[],
    "al":[],
    "zr":[],
    "ca":[],
    "be":[],
    "p":[],
    "s":[]
  }
  public maxmin = [ //判断是否上下限都为null开控制是否显示的
    "c",
    "si",
    "mn",
    "cu",
    "ni",
    "cr",
    "mo",
    "nb",
    "v",
    "ti",
    "als",
    "alt",
    "n",
    "fe",
    "mg",
    "zn",
    "b",
    "w",
    "sb",
    "al",
    "zr",
    "ca",
    "be",
    "p",
    "s",
    "rel1",
    "rel2",
    "rm1",
    "rm2",
    "elong1",
    "elong2",
    "hardness1",
    "hardness2",
    "hardness3",
    "impactp1",
    "impactp2",
    "impactp3"
  ];

  public dataDetail = { //获取的数据格式
    "status":false,
    "c":{
      "max":null,
      "min":null,
    },
    "si":{
      "max":null,
      "min":null,
    },
    "mn":{
      "max":null,
      "min":null,
    },
    "cu":{
      "max":null,
      "min":null,
    },
    "ni":{
      "max":null,
      "min":null,
    },
    "cr":{
      "max":null,
      "min":null,
    },
    "mo":{
      "max":null,
      "min":null,
    },
    "nb":{
      "max":null,
      "min":null,
    },
    "v":{
      "max":null,
      "min":null,
    },
    "ti":{
      "max":null,
      "min":null,
    },
    "als":{
      "max":null,
      "min":null,
    },
    "alt":{
      "max":null,
      "min":null,
    },
    "n":{
      "max":null,
      "min":null,
    },
    "fe":{
      "max":null,
      "min":null,
    },
    "mg":{
      "max":null,
      "min":null,
    },
    "zn":{
      "max":null,
      "min":null,
    },
    "b":{
      "max":null,
      "min":null,
    },
    "w":{
      "max":null,
      "min":null,
    },
    "sb":{
      "max":null,
      "min":null,
    },
    "al":{
      "max":null,
      "min":null,
    },
    "zr":{
      "max":null,
      "min":null,
    },
    "ca":{
      "max":null,
      "min":null,
    },
    "be":{
      "max":null,
      "min":null,
    },
    "p":{
      "max":null,
      "min":null,
    },
    "s":{
      "max":null,
      "min":null,
    },
    "heatcondi":null,//热处理状态
    "rel1":{
      "max":null,
      "min":null,
    },
    "rel2":{
      "max":null,
      "min":null,
    },
    "rm1":{
      "max":null,
      "min":null,
    },
    "rm2":{
      "max":null,
      "min":null,
    },
    "elong1":{//延长率
      "max":null,
      "min":null
    },
    "elong2":{
      "max":null,
      "min":null
    },
    "hardness1":{
      "max":null,
      "min":null
    },//硬度
    "hardness2":{
      "max":null,
      "min":null
    },
    "hardness3":{
      "max":null,
      "min":null
    },
    "impactp1":{//冲击功
      "max":null,
      "min":null
    },
    "impactp2":{
      "max":null,
      "min":null
    },
    "impactp3":{
      "max":null,
      "min":null
    },
    "impacttemp":null,//温度
    "bendaxdia": null,//弯曲直径
    deviation:{
      "c":[],
      "si":[],
      "mn":[],
      "cu":[],
      "ni":[],
      "cr":[],
      "mo":[],
      "nb":[],
      "v":[],
      "ti":[],
      "als":[],
      "alt":[],
      "n":[],
      "fe":[],
      "mg":[],
      "zn":[],
      "b":[],
      "w":[],
      "sb":[],
      "al":[],
      "zr":[],
      "ca":[],
      "be":[],
      "p":[],
      "s":[]
    }//误差
  };

  formatInDate(){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.controls['indate'].value)){
      this.validateForm.controls["indate"].setValue(new Date().getFullYear()+"-"+this.validateForm.controls['indate'].value);
    }else if(!yearMonthDay.test(this.validateForm.controls['indate'].value)){
      this.validateForm.controls["indate"].setValue(null);
    }
  }


  checkForDeveiation(controlname){
    if(!this.deviation[controlname]){
      return [0,0];
    }
    if(this.deviation[controlname].length == 0)//若不存在误差范围，返回0
      return [0,0];
    else{
      for(let dev of this.deviation[controlname]){
        if(this.belongto(this.validateForm.value[controlname],dev.range)){
          return dev.deviation.split('~');
        }
      }
    }
    return [0,0];
  }

  updateMaxMin(item){
    this.validateForm.controls[item].setValidators([Validators.required]);
  }

  Number(num){
    return Number(num);
  }
  belongto(value:number,range:string){ //判断一个元素值属否处于<= | >= | > | <之类的范围中
    if(!range) return 0;
    let dayudengyu = range.indexOf(">=");
    let xiaoyudengyu = range.indexOf("<=");
    let dayu = range.indexOf(">");
    let xiaoyu = range.indexOf("<");
    if(dayudengyu!=-1 && value >= parseFloat(range.slice(dayudengyu + 2,range.length))){
      return parseFloat(range.slice(dayudengyu + 2,range.length));
    }
    if(xiaoyudengyu!=-1 && value <= parseFloat(range.slice(xiaoyudengyu + 2,range.length))){
      return parseFloat(range.slice(xiaoyudengyu + 2,range.length));
    }
    if(dayu!=-1 && value > parseFloat(range.slice(dayu + 1,range.length))){
      return parseFloat(range.slice(dayu + 1,range.length));
    }
    if(xiaoyu!=-1 && value < parseFloat(range.slice(xiaoyu + 1,range.length))){
      return parseFloat(range.slice(xiaoyu + 1,range.length));
    }
  }

  ngOnInit(): void {
    this.materialReinspectionService.getcodedmarking().then((res:any)=>{
      if(res['result']=="success"){
        this.codedmarkings = res.data.filter(item=>res.data.indexOf(item['codedmarking']) == -1)
      }else{
        this.modalService.error({
          nzTitle: '错误',
          nzContent: "没有可供登记的入库编号！"
        });
      }
    });

    this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]],
      "designation":[null, [Validators.required]],
      "stand":[null, [Validators.required]],
      "spec":[null, [Validators.required]],
      "indate":[null, [Validators.required]],
      c:[null],
      si:[null],
      mn:[null],
      cu:[null],
      ni:[null],
      cr:[null],
      mo:[null],
      nb:[null],
      v:[null],
      ti:[null],
      als:[null],
      alt:[null],
      n:[null],
      fe:[null],
      mg:[null],
      zn:[null],
      b:[null],
      w:[null],
      sb:[null],
      al:[null],
      zr:[null],
      ca:[null],
      be:[null],
      p:[null],
      s:[null],
      rel1:[null],
      rel2:[null],
      rm1:[null],
      rm2:[null],
      elong1:[null],
      elong2:[null],
      hardness1:[null],
      hardness2:[null],
      hardness3:[null],
      impactp1:[null],
      impactp2:[null],
      impactp3:[null],
      impacttemp:[null],
      bendangle:[null],
      bendaxdia:[null]
    });
  }
  public chemicalcomposition = true;

  getInfoCompleted(){
    if(this.validateForm.controls['codedmarking'].value!=null && this.validateForm.controls['codedmarking'].value!=""){
      this.materialReinspectionService.searchmatlnotice(this.validateForm.controls['codedmarking'].value).then((res:any)=>{
        if(res['result']=="success"){
          this.validateForm.controls['designation'].setValue(res['data']['designation']);
          this.validateForm.controls['stand'].setValue(res['data']['matlstand']);
          this.validateForm.controls['spec'].setValue(res['data']['spec']);
          this.validateForm.controls['indate'].setValue(res['data']['indate']);
          this.materialReinspectionService.searchrematerialitem(this.validateForm.controls['codedmarking'].value).subscribe(res=>{
            if(res['result'] == "success")
              if(res['data'].length>0)
                this.chemicalcomposition = res['data'][0]['chemicalcomposition'] == '有';
              this.checkForContraststand();
          })
        }
      })
    }
  }

  changeChemicalcompositionDisplay(){//判断化学元素是否需要显示
    let chemicalcomposition = [
        "c",
        "si",
        "mn",
        "cu",
        "ni",
        "cr",
        "mo",
        "nb",
        "v",
        "ti",
        "als",
        "alt",
        "n",
        "fe",
        "mg",
        "zn",
        "b",
        "w",
        "sb",
        "al",
        "zr",
        "ca",
        "be",
        "p",
        "s"
      ];
    if(!this.chemicalcomposition){
      for(let item of chemicalcomposition){
        this.validateForm.controls[item].setValidators([]);
        this.dataDetail[item].max = null;
        this.dataDetail[item].min = null;
      }
    }
  }

  changeForceDisplay(){
    let forces = [
      'rel1',
      'rel2',
      'rm1',
      'rm2',
      'elong1',
      'elong2',
      'hardness1',
      'hardness2',
      'hardness3',
      'impactp1',
      'impactp2',
      'impactp3',
      'impacttemp',
      'bendangle',
      'bendaxdia'
    ];
    for(let item of forces)
      this.validateForm.controls[item].setValidators([]);
  }


  constructor(public materialReinspectionService: MaterialReinspectionService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  changeHardness(name){ //硬度校验
    if(!this.validateForm.controls[name].value)
      this.validateForm.controls[name].setErrors({required: true});
    else if(!/^[0-9]+(.[0-9])?[/][0-9]+(.[0-9])?[/][0-9]+(.[0-9])?$/.test(this.validateForm.controls[name].value)){
      this.validateForm.controls[name].setValue(null);
      this.validateForm.controls[name].setErrors({partern: true, error: true});
    }else{
      let data = this.validateForm.controls[name].value.split('/');
      for(let item of data){
        if(parseFloat(item) > this.dataDetail[name].max || parseFloat(item) < this.dataDetail[name].min) {
          this.validateForm.controls[name].setErrors({overflow: true, error: true});
        }
      }
    }
  }

  //最大最小值检验
  MaxMinJudge(name){
    if (this.dataDetail[name].max == null || this.dataDetail[name].max == 'null') this.dataDetail[name].max = 99999;
    if (this.dataDetail[name].min == null || this.dataDetail[name].min == 'null') this.dataDetail[name].min = 0;
    if (this.validateForm.controls[name].value==null) {
      this.validateForm.controls[name].setErrors({required: true})
    } else if (this.validateForm.controls[name].value >  (Number(this.dataDetail[name].max) + Number(this.checkForDeveiation(name)[1])) || this.validateForm.controls[name].value < (Number(this.dataDetail[name].min) - Number(this.checkForDeveiation(name)[0]))) {
      this.validateForm.controls[name].setErrors({overflow: true, error: true});
    }
  }

  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.materialReinspectionService.putMatlReinspection({
        "codedmarking":this.validateForm.controls['codedmarking'].value,
        "designation":this.validateForm.controls['designation'].value,
        "stand":this.validateForm.controls['stand'].value,
        "spec":this.validateForm.controls['spec'].value,
        "c":this.validateForm.controls['c'].value,
        "mn":this.validateForm.controls['mn'].value,
        "si":this.validateForm.controls['si'].value,
        "p":this.validateForm.controls['p'].value,
        "s":this.validateForm.controls['s'].value,
        "cr":this.validateForm.controls['cr'].value,
        "ni":this.validateForm.controls['ni'].value,
        "ti":this.validateForm.controls['ti'].value,
        "mo":this.validateForm.controls['mo'].value,
        "nb":this.validateForm.controls['nb'].value,
        "cu":this.validateForm.controls['cu'].value,
        "fe":this.validateForm.controls['fe'].value,
        "n":this.validateForm.controls['n'].value,
        "alt":this.validateForm.controls['alt'].value,
        "als":this.validateForm.controls['als'].value,
        "mg":this.validateForm.controls['mg'].value,
        "zn":this.validateForm.controls['zn'].value,
        "v":this.validateForm.controls['v'].value,
        "b":this.validateForm.controls['b'].value,
        "w":this.validateForm.controls['w'].value,
        "sb":this.validateForm.controls['sb'].value,
        "al":this.validateForm.controls['al'].value,
        "zr":this.validateForm.controls['zr'].value,
        "ca":this.validateForm.controls['ca'].value,
        "be":this.validateForm.controls['be'].value,
        "rel1":this.validateForm.controls['rel1'].value,
        "rel2":this.validateForm.controls['rel2'].value,
        "rm1":this.validateForm.controls['rm1'].value,
        "rm2":this.validateForm.controls['rm2'].value,
        "elong1":this.validateForm.controls['elong1'].value,
        "elong2":this.validateForm.controls['elong2'].value,
        "hardness1":this.validateForm.controls['hardness1'].value,
        "hardness2":this.validateForm.controls['hardness2'].value,
        "hardness3":this.validateForm.controls['hardness3'].value,
        "impactp1":this.validateForm.controls['impactp1'].value,
        "impactp2":this.validateForm.controls['impactp2'].value,
        "impactp3":this.validateForm.controls['impactp3'].value,
        "impacttemp":this.validateForm.controls['impacttemp'].value,
        "bendangle":this.validateForm.controls['bendangle'].value,
        "bendaxdia":this.validateForm.controls['bendaxdia'].value,
        "indate":this.validateForm.controls['indate'].value,
        "user":this._storage.get("username")
      }).then((res:any)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }

  searchMatl(){
    this.materialReinspectionService.searchrematerial(this.validateForm.controls['codedmarking'].value).subscribe(res=>{
      if(res['result'] == "success"){
        for(let i in this.validateForm.controls){
          if(i!='codedmarking' && i!="designation" && i!="spec" && i!="stand"){
            this.validateForm.controls[i].enable();
            this.validateForm.controls[i].setValue(null);
          }
        }
        if(res['data'] && res['data'].length>0){
          for(let i in res['data'][0]){
            if(res['data'][0][i]){
              if(this.validateForm.controls[i] && i!='codedmarking' && i!="designation" && i!="spec" && i!="stand"){
                this.validateForm.controls[i].setValue(res['data'][0][i]);
                this.validateForm.controls[i].disable();
              }
            }
          }
        }
      }
    })
  }

  /**
   * 当输入材料标准、牌号都输入完后，会先通过这两项去查询相应标准，若查不到，则检测规格是否输入，已输入则用材料标准、牌号、规格三项查询相应标准内容。
   */
  checkForContraststand(){
    if(this.validateForm.controls['stand'].value!=null && this.validateForm.controls['designation'].value!=null &&this.validateForm.controls['stand'].value!="" && this.validateForm.controls['designation'].value!=""){
      this.materialReinspectionService.contraststand(this.validateForm.controls['stand'].value,this.validateForm.controls['designation'].value ,null).subscribe(res => {
        if(res['result'] == "success") {
          this.dataDetail = res['data'];
          this.dataDetail.status = true;
          this.deviation = this.dataDetail.deviation;
          for (let i =0;i<this.maxmin.length;i++){
            let item = this.maxmin[i];
            if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
              this.validateForm.controls[item].setValidators([Validators.required]);
              if (this.dataDetail[item].max == null || this.dataDetail[item].max == 'null') this.dataDetail[item]['max'] = 99999;
              if (this.dataDetail[item].min == null || this.dataDetail[item].min == 'null') this.dataDetail[item]['min'] = 0;
            }else{
              this.validateForm.controls[item].setValidators([]);
            }
          }
          for(let i =0;i<this.direct.length;i++){
            let item = this.direct[i];
            if (this.dataDetail[item] != null) {
              this.validateForm.controls[item].setValidators([Validators.required]);
            }
          }
          this.changeChemicalcompositionDisplay();
          this.changeForceDisplay();
          this.searchMatl();
        }else if(this.validateForm.controls['stand'].value!=null && this.validateForm.controls['designation'].value!=null && this.validateForm.controls['spec'].value!=null&&this.validateForm.controls['stand'].value!="" && this.validateForm.controls['designation'].value!="" && this.validateForm.controls['spec'].value!=""){
          let specData = this.validateForm.controls['spec'].value;
          if(this.validateForm.controls['spec'].value.indexOf("δ=")!=-1){
            specData = parseFloat(this.validateForm.controls['spec'].value.substring(2,specData.length));
          }
          this.materialReinspectionService.contraststand(this.validateForm.controls['stand'].value,this.validateForm.controls['designation'].value ,specData).subscribe(res => {
            if(res['result'] == "success"){
              this.dataDetail = res['data'];
              this.dataDetail.status = true;
              this.deviation = this.dataDetail.deviation;
              for (let i =0;i<this.maxmin.length;i++){
                let item = this.maxmin[i];
                if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
                  this.validateForm.controls[item].setValidators([Validators.required]);
                  if (this.dataDetail[item].max == null || this.dataDetail[item].max == 'null') this.dataDetail[item]['max'] = 99999;
                  if (this.dataDetail[item].min == null || this.dataDetail[item].min == 'null') this.dataDetail[item]['min'] = 0;
                }else{
                  this.validateForm.controls[item].setValidators([]);
                }
              }
              for(let i =0;i<this.direct.length;i++){
                let item = this.direct[i];
                if (this.dataDetail[item] != null) {
                  this.validateForm.controls[item].setValidators([Validators.required]);
                }
              }
              this.changeChemicalcompositionDisplay();
              this.changeForceDisplay();
              this.searchMatl();
            }
          });
        }
      });
    }else{
      this.dataDetail.status = false;
    }
  }
  /**
   * 判断弯曲直径大小（格式：数组+'a'），输入弯曲直径应大于标准中的直径大小
   */
  judgeBendaxdia(){
    let value = this.validateForm.controls['bendaxdia'].value;
    let exp = /^([1-9]\d*|0)(\.\d{1,2})*(a)$/;
    if(!exp.test(value)){
      this.validateForm.controls["bendaxdia"].setValue(null);
      return;
    }
    if(this.dataDetail.bendaxdia){
      let index = this.dataDetail.bendaxdia.indexOf("a");
      let yaoqiu = parseFloat(this.dataDetail.bendaxdia.substring(0,index));
      let index2 = value.indexOf("a");
      let newValue = parseFloat(value.substring(0,index2));
      if(newValue > yaoqiu){
        this.validateForm.controls["bendaxdia"].setValue(null);
      }
    }

  }

}
