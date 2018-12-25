import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProductTestBoardDataRegistrationService} from "./productTestBoardDataRegistration.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardDataRegistration',
  templateUrl: 'productTestBoardDataRegistration.component.html',
  styleUrls: ['./productTestBoardDataRegistration.component.less'],
  providers: [ProductTestBoardDataRegistrationService]
})
export class ProductTestBoardDataRegistrationComponent implements OnInit {
  private prodnos:any;
  validateForm: FormGroup;
  testboardstandValidateForm: FormGroup;
  dataSet = [];
  testboardstands = [];
  matlstands = [];
  spec : any;
  private designation:any;
  bendparas = [
    {
      name:"面弯",ename:'surfacebending'
    },
    {
      name:"背弯",ename:'backbending'
    },
  ]
  datarange = {
    "rm":{//抗拉强度
      "max":9999,
      "min":0
    },
    "elong":{//延长率
      "max":9999,
      "min":0
    },
    "impacttemp":9999,//温度（冲击温度），最大值
    "impactpa1":{//热影响区冲击功1
      "max":9999,
      "min":0
    },
    "impactpa2":{//热影响区冲击功2
      "max":9999,
      "min":0
    },
    "impactpa3":{//热影响区冲击功3
      "max":9999,
      "min":0
    },
    "impactpb1":{//焊缝区冲击功1
      "max":9999,
      "min":0
    },
    "impactpb2":{//焊缝区冲击功2
      "max":9999,
      "min":0
    },
    "impactpb3":{//焊缝区冲击功3
      "max":9999,
      "min":0
    },
    "bendaxdia":"1.5a",//弯曲直径
  };
  fractposits = [
    {name:"焊缝",ename:"hanfeng"},
    {name:"母材",ename:"mucai"},
  ]

  ngOnInit(): void {
    this.productTestBoardDataRegistrationService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "testboardstand":[null, [Validators.required]],
      "matlstand":[null, [Validators.required]],
      "designation":[null, [Validators.required]],
      "rm":[null, [Validators.required]],
      "elong":[null, [Validators.required]],
      "impacttemp":[null, [Validators.required]],
      "impactpa1":[null, [Validators.required]],
      "impactpa2":[null, [Validators.required]],
      "impactpa3":[null, [Validators.required]],
      "impactpb1":[null, [Validators.required]],
      "impactpb2":[null, [Validators.required]],
      "impactpb3":[null, [Validators.required]],
      "bendpara":[null, [Validators.required]],
      "benddia":[null, [Validators.required]],
      "fractposit":[this.fractposits[0], [Validators.required]],
      "entrustdate":[null, [Validators.required]],
    });
    this.testboardstandValidateForm = this.fb.group({
      "testboardstand":[null, [Validators.required]]
    })
    this.productTestBoardDataRegistrationService.gettestboardstand().subscribe((res)=>{
      if(res['result']=="success"){
        this.testboardstands = res['data'];
      }
    })
    this.productTestBoardDataRegistrationService.getputmaterial().subscribe((res)=>{
      if(res['result']=="success"){
        this.matlstands = res['data']['matlstand'];
        this.designation = res['data']['designation'];
      }
    })
  }

  constructor(private productTestBoardDataRegistrationService: ProductTestBoardDataRegistrationService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }

  searchData(): void {
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.productTestBoardDataRegistrationService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
        }
      })
    }
  }

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
  addtestboardstand(){
    this.testboardstandValidateForm.controls['testboardstand'].markAsDirty();
    this.testboardstandValidateForm.controls['testboardstand'].updateValueAndValidity();
    if(this.testboardstandValidateForm.valid){
      this.productTestBoardDataRegistrationService.addtestboardstand(this.testboardstandValidateForm.value.testboardstand).subscribe((res)=>{
        if(res["result"]=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          this.productTestBoardDataRegistrationService.gettestboardstand().subscribe((res)=>{
            if(res['result']=="success"){
              this.testboardstands = res['data'];
            }
          })
          this.destroyTplModal();
        }
      })
    }
  }
  getdatarange(){
    if(this.validateForm.value.prodno!==null&&this.validateForm.value.prodno!==""&&this.validateForm.value.matlstand!=null&&this.validateForm.value.matlstand!=""&&this.validateForm.value.designation!=null&&this.validateForm.value.designation!="")
    this.productTestBoardDataRegistrationService.getdatarange(this.validateForm.value.prodno,this.validateForm.value.matlstand,this.validateForm.value.designation).subscribe((res)=>{
      if(res['result']=="success"){
        this.datarange = res['data'];
      }
    })
  }
  judgeBenddia(){
    let value = this.validateForm.value.benddia;
    let exp = /^([1-9]\d*|0)(\.\d{1,2})*(a)$/;
    if(!exp.test(value)){
      this.validateForm.controls["benddia"].setValue(null);
      return;
    }
    if(this.datarange.bendaxdia){
      let index = this.datarange.bendaxdia.indexOf("a");
      let yaoqiu = parseFloat(this.datarange.bendaxdia.substring(0,index));
      let index2 = value.indexOf("a");
      let newValue = parseFloat(value.substring(0,index2));
      if(newValue > yaoqiu){
        this.validateForm.controls["benddia"].setValue(null);
      }
    }

  }
  formatEntrustdate(){//日期格式化
      let monthDay = /^([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
      let yearMonthDay = /^[1-9]\d{3}-([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
      if(monthDay.test(this.validateForm.value.entrustdate)){
        this.validateForm.controls["entrustdate"].setValue(new Date().getFullYear()+"-"+this.validateForm.value.entrustdate);
      }else if(!yearMonthDay.test(this.validateForm.value.entrustdate)){
        this.validateForm.controls["entrustdate"].setValue(null);
      }
    }
  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.productTestBoardDataRegistrationService.puttestboardparam({
        "prodno":this.validateForm.value.prodno,
        "testboardstand":this.validateForm.value.testboardstand,
        "matlstand":this.validateForm.value.matlstand,
        "designation":this.validateForm.value.designation,
        "rm":this.validateForm.value.rm,
        "elong":this.validateForm.value.elong,
        "impacttemp":this.validateForm.value.impacttemp,
        "impactpa1":this.validateForm.value.impactpa1,
        "impactpa2":this.validateForm.value.impactpa2,
        "impactpa3":this.validateForm.value.impactpa3,
        "impactpb1":this.validateForm.value.impactpb1,
        "impactpb2":this.validateForm.value.impactpb2,
        "impactpb3":this.validateForm.value.impactpb3,
        "bendpara":this.validateForm.value.bendpara.name,
        "ebendpara":this.validateForm.value.bendpara.ename,
        "benddia":this.validateForm.value.benddia,
        "fractposit":this.validateForm.value.fractposit.name,
        "efractposit":this.validateForm.value.fractposit.ename,
        "entrustdate":this.validateForm.value.entrustdate,
        "user":this._storage.get("username")
      })
    }
  }
}
