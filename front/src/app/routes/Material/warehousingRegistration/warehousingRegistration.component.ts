import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {WarehousingRegistrationService} from "./warehousingRegistration.service";
import {NzNotificationService, NzMessageService, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {NzModalRef} from "ng-zorro-antd/modal";

@Component({
  selector: 'app-warehousingRegistration',
  templateUrl: 'warehousingRegistration.component.html',
  styleUrls: ['./warehousingRegistration.component.less'],
  providers: [WarehousingRegistrationService]
})

export class WarehousingRegistrationComponent implements OnInit {
  private validateForm: FormGroup;
  private millunitValidateForm: FormGroup;
  private supplierValidateForm: FormGroup;
  private matlnameValidateForm: FormGroup;
  private modelstandValidateForm: FormGroup;
  private warrantysitu:any;
  private matlname:any;
  private matlstand:any;
  private modelstand:any;
  private supplier:any;
  private millunits:any;
  private designation:any;

  private direct = [ //直接判断是否为null来控制是否现实的
    "heatcondi",
    "impacttemp",
    "bendaxdia",
    "utclass"
  ];
  private maxmin = [ //判断是否上下限都为null开控制是否显示的
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

  private dataDetail = { //获取的数据格式
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
    "Elong2":{
      "max":null,
      "min":null
    },
    "hardness1":null,//硬度
    "hardness2":null,
    "hardness3":null,
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
    "note":null,
    "bendaxdia": null,//弯曲直径
    "utclass":null//UT级别
  };
  private notes = []; //备注选项
  private warrantynos = []; //质保书号选项
  private utclass = {//ut等级罗马字母转阿拉伯
    "I":1,
    "II":2,
    "III":3,
    "IV":4
  };
  private utclass2 = {//ut等级阿拉伯转罗马字母
    "1":"I",
    "2":"II",
    "3":"III",
    "4":"IV"
  };
  private specs = [];//规格选项

  onSpecInput(value: string): void { //当规格输入时展开选项
    this.specs = value ? [
      value,
      "φ"+value,
      "δ="+value,
      "EHA"+value,
      "∠"+value,
      "DHB"+value,
      "T"+value,
      "12~40目",
      "10~60目"
    ]:[
      "φ"+value,
      "δ="+value,
      "EHA"+value,
      "∠"+value,
      "DHB"+value,
      "T"+value,
      "12~40目",
      "10~60目"
    ];
  }

  onWarrantynoInput(value:string): void { //当质保书号输入时展开选项
    this.warrantynos = value ? [
      value,
      "质保书未到",
      "尽量少用",
      "直发同泰"
    ] : [
      "质保书未到",
      "尽量少用",
      "直发同泰"
    ];

  }

  onNoteInput(value:string):void{ //当备注输入时展开选项
    this.notes = value ? [
      value,
      "实物未到",
      "同 一张质保书",
      "有厂名",
      "质保书不合格",
      "不得用于压力容器",
      "元素含量超标"
    ]: [
      "实物未到",
      "同 一张质保书",
      "有厂名",
      "质保书不合格",
      "不得用于压力容器",
      "元素含量超标"
    ];
  }

  private units = [ //单位选项
    " ",
    "KG",
    "m",
    "张",
    "支"
  ];
  private utclasses = [];
  private bendangles = [ //角度选项
    "OK",
    "180",
    "120"
  ];
  formatInDate(){ //日期格式化
    let monthDay = /^([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.controls["indate"].value)){
      this.validateForm.controls["indate"].setValue(new Date().getFullYear()+"-"+this.validateForm.controls["indate"].value);
    }else if(!yearMonthDay.test(this.validateForm.controls["indate"].value)){
      this.validateForm.controls["indate"].setValue(null);
    }
  }

  constructor(private fb: FormBuilder, private router: Router,private modalService: NzModalService, private warehousingregistrationService:WarehousingRegistrationService,private notification: NzNotificationService,private message : NzMessageService, private storage: SessionStorageService) {
    this.warehousingregistrationService.getputmaterial().subscribe(res => {
      if (res['result'] === 'success') {
        this.warrantysitu = res['data']['warrantysitu'];
        this.matlname = res['data']['matlname'];
        this.matlstand = res['data']['matlstand'];
        this.modelstand = res['data']['modelstand'];
        this.supplier = res['data']['supplier'];
        this.millunits = res['data']['millunit'];
        this.designation = res['data']['designation'];
      }
    });
  }

  getFormControl(name) {
    return this.validateForm.controls[name];
  }


  /**
   * 当输入材料标准、牌号都输入完后，会先通过这两项去查询相应标准，若查不到，则检测规格是否输入，已输入则用材料标准、牌号、规格三项查询相应标准内容。
   */
  checkForContraststand(){
    if(this.validateForm.controls['matlstand'].value!=null && this.validateForm.controls['designation'].value!=null &&this.validateForm.controls['matlstand'].value!="" && this.validateForm.controls['designation'].value!=""){
      this.warehousingregistrationService.contraststand(this.validateForm.controls['matlstand'].value,this.validateForm.controls['designation'].value ,null).subscribe(res => {
        if(res['result'] == "success") {
          this.dataDetail = res['data'];
          this.dataDetail.status = true;
          this.notification.remove();
          this.notification.blank('注意', this.dataDetail.note, {
            nzDuration: 0,
            nzStyle: {
              color: "red"
            }
          });
          switch (this.dataDetail.utclass) {
            case 1 :
              this.utclasses = [
                "I"
              ];
              break;
            case 2 :
              this.utclasses = [
                "I",
                "II"
              ];
              break;
            case 3 :
              this.utclasses = [
                "I",
                "II",
                "III"
              ];
              break;
            case 4 :
              this.utclasses = [
                "I",
                "II",
                "III",
                "IV"
              ];
              break;
          }
          for (let i =0;i<this.maxmin.length;i++){
            let item = this.maxmin[i];
            if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
              this.validateForm.controls[item].setValidators([Validators.required]);
            }
          }
          for(let i =0;i<this.direct.length;i++){
            let item = this.direct[i];
            if (this.dataDetail[item] != null) {
              this.validateForm.controls[item].setValidators([Validators.required]);
            }
          }
        }else if(this.validateForm.controls['matlstand'].value!=null && this.validateForm.controls['designation'].value!=null && this.validateForm.controls['spec'].value!=null&&this.validateForm.controls['matlstand'].value!="" && this.validateForm.controls['designation'].value!="" && this.validateForm.controls['spec'].value!=""){
          let specData = this.validateForm.controls['spec'].value;
          if(this.validateForm.controls['spec'].value.indexOf("δ=")!=-1){
            specData = parseFloat(this.validateForm.controls['spec'].value.substring(2,specData.length));
          };
          this.warehousingregistrationService.contraststand(this.validateForm.controls['matlstand'].value,this.validateForm.controls['designation'].value ,specData).subscribe(res => {
            if(res['result'] == "success"){
              this.dataDetail = res['data'];
              this.dataDetail.status = true;
              this.notification.remove();
          this.notification.blank('注意', this.dataDetail.note, {
                nzDuration: 0,
                nzStyle: {
                  color:"red"
                }
              });
              switch(this.dataDetail.utclass){
                case 1 :
                  this.utclasses = [
                    "I"
                  ];
                  break;
                case 2 :
                  this.utclasses = [
                    "I",
                    "II"
                  ];
                  break;
                case 3 :
                  this.utclasses = [
                    "I",
                    "II",
                    "III"
                  ];
                  break;
                case 4 :
                  this.utclasses = [
                    "I",
                    "II",
                    "III",
                    "IV"
                  ];
                  break;
              }
              for (let i =0;i<this.maxmin.length;i++){
                let item = this.maxmin[i];
                if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
                  this.validateForm.controls[item].setValidators([Validators.required]);
                }
              }
              for(let i =0;i<this.direct.length;i++){
                let item = this.direct[i];
                if (this.dataDetail[item] != null) {
                  this.validateForm.controls[item].setValidators([Validators.required]);
                }
              }
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
    let value = this.validateForm["controls"]["bendaxdia"].value;
    let exp = /^[0-9]*(a)$/;
    if(!exp.test(value)){
      this.validateForm["controls"]["bendaxdia"].setValue(null);
      return;
    }
    if(this.dataDetail.bendaxdia){
      let index = this.dataDetail.bendaxdia.indexOf("a");
      let yaoqiu = parseFloat(this.dataDetail.bendaxdia.substring(0,index));
      let index2 = value.indexOf("a");
      let newValue = parseFloat(value.substring(0,index2));
      if(newValue > yaoqiu){
        this.validateForm["controls"]["bendaxdia"].setValue(null);
      }
    }

  }

  submitForm() {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    let data = {
      "user":this.storage.get("username"),
      "codedmarking":this.validateForm["controls"]["codedmarking"].value,
      "warrantysitu":this.validateForm["controls"]["warrantysitu"].value,
      "note":this.validateForm["controls"]["note"].value,
      "indate":this.validateForm["controls"]["indate"].value,
      "matlname":this.validateForm["controls"]["matlname"].value,
      "warrantyno":this.validateForm["controls"]["warrantyno"].value,
      "matlstand":this.validateForm["controls"]["matlstand"].value,
      "modelstand":this.validateForm["controls"]["modelstand"].value,
      "supplier":this.validateForm["controls"]["supplier"].value,
      "designation":this.validateForm["controls"]["designation"].value,
      "spec":this.validateForm["controls"]["spec"].value,
      "qty":this.validateForm["controls"]["qty"].value,
      "unit":this.validateForm["controls"]["unit"].value,
      "dimension":this.validateForm["controls"]["dimension"].value,
      "millunit":this.validateForm["controls"]["millunit"].value,
      "heatbatchno":this.validateForm["controls"]["heatbatchno"].value,
      "c":this.validateForm["controls"]["c"].value,
      "si":this.validateForm["controls"]["si"].value,
      "mn":this.validateForm["controls"]["mn"].value,
      "cu":this.validateForm["controls"]["cu"].value,
      "ni":this.validateForm["controls"]["ni"].value,
      "cr":this.validateForm["controls"]["cr"].value,
      "mo":this.validateForm["controls"]["mo"].value,
      "nb":this.validateForm["controls"]["nb"].value,
      "v":this.validateForm["controls"]["v"].value,
      "ti":this.validateForm["controls"]["ti"].value,
      "als":this.validateForm["controls"]["als"].value,
      "alt":this.validateForm["controls"]["alt"].value,
      "n":this.validateForm["controls"]["n"].value,
      "fe":this.validateForm["controls"]["fe"].value,
      "mg":this.validateForm["controls"]["mg"].value,
      "zn":this.validateForm["controls"]["zn"].value,
      "b":this.validateForm["controls"]["b"].value,
      "w":this.validateForm["controls"]["w"].value,
      "sb":this.validateForm["controls"]["sb"].value,
      "al":this.validateForm["controls"]["al"].value,
      "zr":this.validateForm["controls"]["zr"].value,
      "ca":this.validateForm["controls"]["ca"].value,
      "be":this.validateForm["controls"]["be"].value,
      "p":this.validateForm["controls"]["p"].value,
      "s":this.validateForm["controls"]["s"].value,
      "heatcondi":this.validateForm["controls"]["heatcondi"].value,
      "rel1":this.validateForm["controls"]["rel1"].value,
      "rel2":this.validateForm["controls"]["rel2"].value,
      "rm1":this.validateForm["controls"]["rm1"].value,
      "rrm2":this.validateForm["controls"]["rm2"].value,
      "elong1":this.validateForm["controls"]["elong1"].value,
      "elong2":this.validateForm["controls"]["elong2"].value,
      "hardness1":this.validateForm["controls"]["hardness1"].value,
      "hardness2":this.validateForm["controls"]["hardness2"].value,
      "hardness3":this.validateForm["controls"]["hardness3"].value,
      "impactp1":this.validateForm["controls"]["impactp1"].value,
      "impactp2":this.validateForm["controls"]["impactp2"].value,
      "impactp3":this.validateForm["controls"]["impactp3"].value,
      "impacttemp":this.validateForm["controls"]["impacttemp"].value,
      "bendangle":this.validateForm["controls"]["bendangle"].value,
      "bendaxdia":this.validateForm["controls"]["bendaxdia"].value,
      "utclass":this.utclass[this.validateForm["controls"]["utclass"].value]
    };
    if (this.validateForm.valid) {
      this.warehousingregistrationService.submitForm(data).subscribe(res => {
        if (res['result'] == "success") {
          const modal = this.modalService.success({
            nzTitle: '录入成功',
            nzContent: '3秒后将回到首页'
          });

          window.setTimeout(() => this.backtoDashboard(modal), 3000);

        }else{
          this.message.error("提交失败，请检查输入的数据后重新提交！");

        }
      })
    }
  }


  backtoDashboard(modal){
    modal.destroy();
    this.router.navigateByUrl("/dashboard");
  }

  //通过入库编号查询其他所有内容，并根据规格、牌号、材料标准控制显隐
  getInfoByCodedmarking(e?: MouseEvent){
    if (e) {
      e.preventDefault();
    }
    if(this.validateForm.controls['codedmarking'].value==null || this.validateForm.controls['codedmarking'].value==""){
      this.message.error("入库编号不能为空！");
    }else{
      this.warehousingregistrationService.getmaterialByCodedmarking(this.validateForm.controls['codedmarking'].value).subscribe(res => {
        if (res['result'] == "success") {
          this.warehousingregistrationService.contraststand(res['data']['matlstand'],res['data']['designation'],null).subscribe(res1 => {
            if(res1['result'] == "success") {
              this.dataDetail = res1['data'];
              this.dataDetail.status = true;
              this.notification.remove();
          this.notification.blank('注意', this.dataDetail.note, {
                nzDuration: 0,
                nzStyle: {
                  color: "red"
                }
              });
              switch (this.dataDetail.utclass) {
                case 1 :
                  this.utclasses = [
                    "I"
                  ];
                  break;
                case 2 :
                  this.utclasses = [
                    "I",
                    "II"
                  ];
                  break;
                case 3 :
                  this.utclasses = [
                    "I",
                    "II",
                    "III"
                  ];
                  break;
                case 4 :
                  this.utclasses = [
                    "I",
                    "II",
                    "III",
                    "IV"
                  ];
                  break;
              }
              for (let i =0;i<this.maxmin.length;i++){
                let item = this.maxmin[i];
                if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
                  this.validateForm.controls[item].setValidators([Validators.required]);
                }
              }
              for(let i =0;i<this.direct.length;i++){
                let item = this.direct[i];
                if (this.dataDetail[item] != null) {
                  this.validateForm.controls[item].setValidators([Validators.required]);
                }
              }
            }else{
              let specData = res['data']['spec'];
              if(res['data']['spec'].indexOf("δ=")!=-1){
                specData = parseFloat(res['data']['spec'].substring(2,specData.length));
              }
              this.warehousingregistrationService.contraststand(res['data']['matlstand'],res['data']['designation'],specData).subscribe(res2 => {
                if(res2['result'] == "success"){
                  this.dataDetail = res2['data'];
                  this.dataDetail.status = true;
                  this.notification.remove();
          this.notification.blank('注意', this.dataDetail.note, {
                    nzDuration: 0,
                    nzStyle: {
                      color:"red"
                    }
                  });
                  switch(this.dataDetail.utclass){
                    case 1 :
                      this.utclasses = [
                        "I"
                      ];
                      break;
                    case 2 :
                      this.utclasses = [
                        "I",
                        "II"
                      ];
                      break;
                    case 3 :
                      this.utclasses = [
                        "I",
                        "II",
                        "III"
                      ];
                      break;
                    case 4 :
                      this.utclasses = [
                        "I",
                        "II",
                        "III",
                        "IV"
                      ];
                      break;
                  }
                  for (let i =0;i<this.maxmin.length;i++){
                    let item = this.maxmin[i];
                    if (this.dataDetail[item].max != null || this.dataDetail[item].min != null) {
                      this.validateForm.controls[item].setValidators([Validators.required]);
                    }
                  }
                  for(let i =0;i<this.direct.length;i++){
                    let item = this.direct[i];
                    if (this.dataDetail[item] != null) {
                      this.validateForm.controls[item].setValidators([Validators.required]);
                    }
                  }
                }
              });
            }
          });
          for (const i in res['data']) {
            if(res['data'][ i ]!=null){
              if("utclass" == i){
                console.log(i,res['data'][i]);
                this.validateForm.controls[i].setValue(this.utclass2[res['data'][i]]);
              } else{
                this.validateForm.controls[i].setValue(res['data'][i]);
              }
            }
          }
        }else{
          this.message.error("请核对入库编号是否填写正确！");
        }
      })
    }
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      codedmarking: [null,[Validators.required]],
      warrantysitu: [null],
      note:[ null ],
      indate:[null,[Validators.required]],
      matlname:[null,[Validators.required]],
      warrantyno:[null],
      matlstand:[null,[Validators.required]],
      modelstand:[null],
      supplier:[null],
      designation:[null,[Validators.required]],
      spec:[null],
      qty:[null,[Validators.required]],
      unit:[null,[Validators.required]],
      dimension:[null,[Validators.required]],
      millunit:[null,[Validators.required]],
      heatbatchno:[null,[Validators.required]],
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
      heatcondi:[null],
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
      utclass:[null],
      bendangle:[null],
      bendaxdia:[null]
    });
    this.millunitValidateForm = this.fb.group({
      millunit:[null,[Validators.required]],
      millunitename:[null,[Validators.required]],
    });
    this.supplierValidateForm = this.fb.group({
      supplier:[null,[Validators.required]]
    });
    this.matlnameValidateForm = this.fb.group({
      matlname:[null, [Validators.required]]
    });
    this.modelstandValidateForm = this.fb.group({
      modelstand:[null, [Validators.required]]
    });
  };

  private tplModal: NzModalRef;
  private tplModalButtonLoading = false;

  createTplModal(tplTitle: TemplateRef<{}>, tplContent: TemplateRef<{}>, tplFooter: TemplateRef<{}>): void {
    this.tplModal = this.modalService.create({
      nzTitle: tplTitle,
      nzContent: tplContent,
      nzFooter: tplFooter,
      nzMaskClosable: false,
      nzClosable: true,
      nzOnOk: null
    });
  }
  destroyTplModal(): void {
    this.tplModalButtonLoading = true;
    window.setTimeout(() => {
      this.tplModalButtonLoading = false;
      this.tplModal.destroy();
    }, 1000);
  }

  submitInfo(which){
    if(which == "millunit"){
      for (const i in this.millunitValidateForm.controls) {
        this.millunitValidateForm.controls[ i ].markAsDirty();
        this.millunitValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.millunitValidateForm.valid) {
        this.warehousingregistrationService.addMillunit(this.millunitValidateForm.controls['millunit'].value,this.millunitValidateForm.controls['millunitename'].value).subscribe(res => {
          if (res['result'] == "success") {
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.destroyTplModal();
          }else{
            this.message.error("添加失败，请稍后重试！");
            this.destroyTplModal();
          }
        });
      }
    }
    else if(which == "supplier"){
      for (const i in this.supplierValidateForm.controls) {
        this.supplierValidateForm.controls[ i ].markAsDirty();
        this.supplierValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.supplierValidateForm.valid){
        this.warehousingregistrationService.addSupplier(this.supplierValidateForm.controls['supplier'].value).subscribe(res =>{
          if(res['result'] == "success"){
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.destroyTplModal();
          }else{
            this.message.error("添加失败，请稍后重试！");
            this.destroyTplModal();
          }
        });
      }
    }else if(which == "matlname"){
      for (const i in this.matlnameValidateForm.controls) {
        this.matlnameValidateForm.controls[ i ].markAsDirty();
        this.matlnameValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.matlnameValidateForm.valid){
        this.warehousingregistrationService.addMatlname(this.matlnameValidateForm.controls['matlname'].value).subscribe(res=>{
          if(res['result'] == "success"){
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.destroyTplModal();
          }else{
            this.message.error("添加失败，请稍后重试！");
            this.destroyTplModal();
          }
        });
      }
    }else if(which == "modelstand"){
      for (const i in this.modelstandValidateForm.controls) {
        this.modelstandValidateForm.controls[ i ].markAsDirty();
        this.modelstandValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.modelstandValidateForm.valid) {
        this.warehousingregistrationService.addModelstand(this.modelstandValidateForm.controls['modelstand'].value).subscribe(res => {
          if (res['result'] == "success") {
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.destroyTplModal();
          } else {
            this.message.error("添加失败，请稍后重试！");
            this.destroyTplModal();
          }
        })
      }
    }
  }
}
