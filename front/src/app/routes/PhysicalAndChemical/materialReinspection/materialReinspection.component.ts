import {Component, OnInit, TemplateRef} from '@angular/core';
import {MaterialReinspectionService} from "./materialReinspection.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

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
  };

  formatInDate(){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.value.indate)){
      this.validateForm.controls["indate"].setValue(new Date().getFullYear()+"-"+this.validateForm.value.indate);
    }else if(!yearMonthDay.test(this.validateForm.value.indate)){
      this.validateForm.controls["indate"].setValue(null);
    }
  }

  ngOnInit(): void {
    this.materialReinspectionService.getcodedmarking().then((res:any)=>{
      if(res['result']=="success"){
        this.codedmarkings = [];
        for(let item of res.data){
          if(this.codedmarkings.indexOf(item['codedmarking']) == -1){
            this.codedmarkings.push(item['codedmarking']);
          }
        }
        console.log(this.codedmarkings);
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
  autoSelect(){
    if(this.validateForm.value.codedmarking!=null && this.validateForm.value.codedmarking!=""){
      this.materialReinspectionService.searchmatlnotice(this.validateForm.value.codedmarking).then((res:any)=>{
        if(res['result']=="success"){
          this.validateForm.controls['designation'].setValue(res['data']['designation']);
          this.validateForm.controls['stand'].setValue(res['data']['matlstand']);
          this.validateForm.controls['spec'].setValue(res['data']['spec']);
          this.validateForm.controls['indate'].setValue(res['data']['indate']);
          this.checkForContraststand();
        }
      })
    }
  }
  constructor(public materialReinspectionService: MaterialReinspectionService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.materialReinspectionService.putMatlReinspection({
        "codedmarking":this.validateForm.value.codedmarking,
        "designation":this.validateForm.value.designation,
        "stand":this.validateForm.value.stand,
        "spec":this.validateForm.value.spec,
        "c":this.validateForm.value.c,
        "mn":this.validateForm.value.mn,
        "si":this.validateForm.value.si,
        "p":this.validateForm.value.p,
        "s":this.validateForm.value.s,
        "cr":this.validateForm.value.cr,
        "ni":this.validateForm.value.ni,
        "ti":this.validateForm.value.ti,
        "mo":this.validateForm.value.mo,
        "nb":this.validateForm.value.nb,
        "cu":this.validateForm.value.cu,
        "fe":this.validateForm.value.fe,
        "n":this.validateForm.value.n,
        "alt":this.validateForm.value.alt,
        "als":this.validateForm.value.als,
        "mg":this.validateForm.value.mg,
        "zn":this.validateForm.value.zn,
        "v":this.validateForm.value.v,
        "b":this.validateForm.value.b,
        "w":this.validateForm.value.w,
        "sb":this.validateForm.value.sb,
        "al":this.validateForm.value.al,
        "zr":this.validateForm.value.zr,
        "ca":this.validateForm.value.ca,
        "be":this.validateForm.value.be,
        "rel1":this.validateForm.value.rel1,
        "rel2":this.validateForm.value.rel2,
        "rm1":this.validateForm.value.rm1,
        "rm2":this.validateForm.value.rm2,
        "elong1":this.validateForm.value.elong1,
        "elong2":this.validateForm.value.elong2,
        "hardness1":this.validateForm.value.hardness1,
        "hardness2":this.validateForm.value.hardness2,
        "hardness3":this.validateForm.value.hardness3,
        "impactp1":this.validateForm.value.impactp1,
        "impactp2":this.validateForm.value.impactp2,
        "impactp3":this.validateForm.value.impactp3,
        "impacttemp":this.validateForm.value.impacttemp,
        "bendangle":this.validateForm.value.bendangle,
        "bendaxdia":this.validateForm.value.bendaxdia,
        "indate":this.validateForm.value.indate,
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

  /**
   * 当输入材料标准、牌号都输入完后，会先通过这两项去查询相应标准，若查不到，则检测规格是否输入，已输入则用材料标准、牌号、规格三项查询相应标准内容。
   */
  checkForContraststand() {
    if(this.validateForm.value.codedmarking!=null && this.validateForm.value.codedmarking!="") {
      let specData = this.validateForm.value.spec;
      if (this.validateForm.value.spec.indexOf("δ=") != -1) {
        specData = parseFloat(this.validateForm.value.spec.substring(2, specData.length));
      }
      this.materialReinspectionService.contraststand(this.validateForm.value.stand, this.validateForm.value.designation, specData).subscribe(res => {
        if (res['result'] == "success") {
          this.dataDetail = res['data'];
          this.dataDetail.status = true;
          for (let i = 0; i < this.maxmin.length; i++) {
            let item = this.maxmin[i];
            if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
              this.validateForm.controls[item].setValidators([Validators.required]);
            }
          }
          for (let i = 0; i < this.direct.length; i++) {
            let item = this.direct[i];
            if (this.dataDetail[item] != null) {
              this.validateForm.controls[item].setValidators([Validators.required]);
            }
          }
        }
      })
    }
  }

  /**
   * 判断弯曲直径大小（格式：数组+'a'），输入弯曲直径应大于标准中的直径大小
   */
  judgeBendaxdia(){
    let value = this.validateForm.value.bendaxdia;
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
