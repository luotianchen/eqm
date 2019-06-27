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
  rematerialForms:any;

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

  formatInDate(control){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }


  checkForDeveiation(form,controlname){
    if(!this.deviation[controlname]){
      return [0,0];
    }
    if(this.deviation[controlname].length == 0)//若不存在误差范围，返回0
      return [0,0];
    else{
      for(let dev of this.deviation[controlname]){
        if(this.belongto(form.controls[controlname].value,dev.range)){
          return dev.deviation.split('~');
        }
      }
    }
    return [0,0];
  }

  updateMaxMin(form,item){
    form.controls[item].setValidators([Validators.required]);
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
    this.searchMatl();
    if(this.validateForm.controls['codedmarking'].value!=null && this.validateForm.controls['codedmarking'].value!=""){
      this.materialReinspectionService.searchmatlnotice(this.validateForm.controls['codedmarking'].value).then((res:any)=>{
        if(res['result']=="success"){
          this.validateForm.controls['designation'].setValue(res['data']['designation']);
          this.validateForm.controls['stand'].setValue(res['data']['matlstand']);
          this.validateForm.controls['spec'].setValue(res['data']['spec']);
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

  changeChemicalcompositionDisplay(form){//判断化学元素是否需要显示
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
        form.controls[item].setValidators([]);
        this.dataDetail[item].max = null;
        this.dataDetail[item].min = null;
      }
    }
  }

  changeForceDisplay(form){
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
      form.controls[item].setValidators([]);
  }


  constructor(public materialReinspectionService: MaterialReinspectionService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  changeHardness(control,name){ //硬度校验
    if(!control.value)
      control.setErrors({required: true});
    else if(!/^[0-9]+(.[0-9])?[/][0-9]+(.[0-9])?[/][0-9]+(.[0-9])?$/.test(control.value)){
      control.setValue(null);
      control.setErrors({partern: true, error: true});
    }else{
      let data = control.value.split('/');
      for(let item of data){
        if(parseFloat(item) > this.dataDetail[name].max || parseFloat(item) < this.dataDetail[name].min) {
          control.setErrors({overflow: true, error: true});
        }
      }
    }
  }

  //最大最小值检验
  MaxMinJudge(form,name){
    if (this.dataDetail[name].max == null || this.dataDetail[name].max == 'null') this.dataDetail[name].max = 99999;
    if (this.dataDetail[name].min == null || this.dataDetail[name].min == 'null') this.dataDetail[name].min = 0;
    if (form.controls[name].value==null) {
      form.controls[name].setErrors({required: true})
    } else if (form.controls[name].value > Number(this.dataDetail[name].max) + Number(this.checkForDeveiation(form,name)[1])  || form.controls[name].value < Number(this.dataDetail[name].min) - Number(this.checkForDeveiation(form,name)[0])) {
      form.controls[name].setErrors({overflow: true, error: true});
      }
  }

  submitForm(index){
    let form = this.rematerialForms[index];
    for(const i in form.controls){
      form.controls[ i ].markAsDirty();
      form.controls[ i ].updateValueAndValidity();
    }
    if(form.valid){
      this.materialReinspectionService.putMatlReinspection({
        "codedmarking":this.validateForm.controls['codedmarking'].value,
        "designation":this.validateForm.controls['designation'].value,
        "stand":this.validateForm.controls['stand'].value,
        "spec":this.validateForm.controls['spec'].value,
        "c":form.controls['c'].value,
        "mn":form.controls['mn'].value,
        "si":form.controls['si'].value,
        "p":form.controls['p'].value,
        "s":form.controls['s'].value,
        "cr":form.controls['cr'].value,
        num:index+1,
        "ni":form.controls['ni'].value,
        "ti":form.controls['ti'].value,
        "mo":form.controls['mo'].value,
        "nb":form.controls['nb'].value,
        "cu":form.controls['cu'].value,
        "fe":form.controls['fe'].value,
        "n":form.controls['n'].value,
        "alt":form.controls['alt'].value,
        "als":form.controls['als'].value,
        "mg":form.controls['mg'].value,
        "zn":form.controls['zn'].value,
        "v":form.controls['v'].value,
        "b":form.controls['b'].value,
        "w":form.controls['w'].value,
        "sb":form.controls['sb'].value,
        "al":form.controls['al'].value,
        "zr":form.controls['zr'].value,
        "ca":form.controls['ca'].value,
        "be":form.controls['be'].value,
        "rel1":form.controls['rel1'].value,
        "rel2":form.controls['rel2'].value,
        "rm1":form.controls['rm1'].value,
        "rm2":form.controls['rm2'].value,
        "elong1":form.controls['elong1'].value,
        "elong2":form.controls['elong2'].value,
        "hardness1":form.controls['hardness1'].value,
        "hardness2":form.controls['hardness2'].value,
        "hardness3":form.controls['hardness3'].value,
        "impactp1":form.controls['impactp1'].value,
        "impactp2":form.controls['impactp2'].value,
        "impactp3":form.controls['impactp3'].value,
        "impacttemp":form.controls['impacttemp'].value,
        "bendangle":form.controls['bendangle'].value,
        "bendaxdia":form.controls['bendaxdia'].value,
        "indate":form.controls['indate'].value,
        "user":this._storage.get("username")
      }).then((res:any)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          form.reset();
        }
      })
    }
  }

  searchMatl(){
    this.materialReinspectionService.searchrematerial(this.validateForm.controls['codedmarking'].value).subscribe(res=>{
      if(res['result'] == "success"){
        this.rematerialForms=[];
        let fbb:FormGroup;
        for(let rematl of res['data']){
          fbb=this.fb.group({
            al:rematl.al,
            als: rematl.als,
            alt: rematl.alt,
            b: rematl.b,
            be: rematl.be,
            bendangle:rematl.bendangle,
            bendaxdia:rematl.bendaxdia,
            c: rematl.c,
            ca: rematl.ca,
            cr: rematl.cr,
            cu: rematl.cu,
            elong1: rematl.elong1,
            elong2:rematl.elong2,
            fe:rematl.fe,
            hardness1: rematl.hardness1,
            hardness2: rematl.hardness2,
            hardness3: rematl.hardness3,
            impactp1: rematl.impactp1,
            impactp2: rematl.impactp2,
            impactp3: rematl.impactp3,
            impacttemp:rematl.impacttemp,
            indate: [rematl.indate,[Validators.required]],
            mg: rematl.mg,
            mn: rematl.mn,
            mo: rematl.mo,
            n:rematl.n,
            nb: rematl.nb,
            ni: rematl.ni,
            p: rematl.p,
            rel1: rematl.rel1,
            rel2: rematl.rel2,
            rm1: rematl.rm1,
            rm2: rematl.rm2,
            s: rematl.s,
            sb: rematl.sb,
            si: rematl.si,
            spec: rematl.spec,
            stand: rematl.stand,
            ti: rematl.ti,
            v: rematl.v,
            w: rematl.w,
            zn: rematl.zn,
            zr: rematl.zr
          })
          this.rematerialForms.push(fbb);
        }
      }
    })
  }

  fillMatl(){
    for (let form of this.rematerialForms) {
      for(let i in form.controls){
        if(i!='codedmarking' && i!="designation" && i!="spec" && i!="stand" && i!="date"){
          form.controls[i].enable();
        }
      }
      if(this.rematerialForms && this.rematerialForms.length>0){
        for(let i in form.controls){
          if(form.controls[i]){
            if(form.controls[i].value && i!='codedmarking' && i!="designation" && i!="spec" && i!="stand"&& i!="date"){
              form.controls[i].disable();
            }
          }
        }
      }
    }
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
          for (let x=0;x<this.rematerialForms.length;x++){
            for (let i =0;i<this.maxmin.length;i++){
              let item = this.maxmin[i];
              if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
                this.rematerialForms[x].controls[item].setValidators([Validators.required]);
                if (this.dataDetail[item].max == null || this.dataDetail[item].max == 'null') this.dataDetail[item]['max'] = 99999;
                if (this.dataDetail[item].min == null || this.dataDetail[item].min == 'null') this.dataDetail[item]['min'] = 0;
              }else{
                this.rematerialForms[x].controls[item].setValidators([]);
              }
            }
            for(let i =0;i<this.direct.length;i++){
              let item = this.direct[i];
              if (this.dataDetail[item] != null) {
                this.rematerialForms[x].controls[item].setValidators([Validators.required]);
              }
            }
            this.changeChemicalcompositionDisplay(this.rematerialForms[x]);
            this.changeForceDisplay(this.rematerialForms[x]);
          }
          this.fillMatl();
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
              for (let x=0;x<this.rematerialForms.length;x++){
                for (let i =0;i<this.maxmin.length;i++){
                  let item = this.maxmin[i];
                  if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
                    this.rematerialForms[x].controls[item].setValidators([Validators.required]);
                    if (this.dataDetail[item].max == null || this.dataDetail[item].max == 'null') this.dataDetail[item]['max'] = 99999;
                    if (this.dataDetail[item].min == null || this.dataDetail[item].min == 'null') this.dataDetail[item]['min'] = 0;
                  }else{
                    this.rematerialForms[x].controls[item].setValidators([]);
                  }
                }
                for(let i =0;i<this.direct.length;i++){
                  let item = this.direct[i];
                  if (this.dataDetail[item] != null) {
                    this.rematerialForms[x].controls[item].setValidators([Validators.required]);
                  }
                }
                this.changeChemicalcompositionDisplay(this.rematerialForms[x]);
                this.changeForceDisplay(this.rematerialForms[x]);
              }
              this.fillMatl();
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
  judgeBendaxdia(form){
    let value = form.value;
    let exp = /^([1-9]\d*|0)(\.\d{1,2})*(a)$/;
    if(!exp.test(value)){
      form.setValue(null);
      return;
    }
    if(this.dataDetail.bendaxdia){
      let index = this.dataDetail.bendaxdia.indexOf("a");
      let yaoqiu = parseFloat(this.dataDetail.bendaxdia.substring(0,index));
      let index2 = value.indexOf("a");
      let newValue = parseFloat(value.substring(0,index2));
      if(newValue > yaoqiu){
        form.controls["bendaxdia"].setValue(null);
      }
    }
  }
}
