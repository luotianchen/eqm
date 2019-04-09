import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProductTestBoardDataRegistrationService} from "./productTestBoardDataRegistration.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {isNumber} from "util";
import {NzModalRef, NzModalService, NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-productTestBoardDataRegistration',
  templateUrl: 'productTestBoardDataRegistration.component.html',
  styleUrls: ['./productTestBoardDataRegistration.component.less'],
  providers: [ProductTestBoardDataRegistrationService]
})
export class ProductTestBoardDataRegistrationComponent implements OnInit {
  validateForm: FormGroup;
  testboardstandValidateForm: FormGroup;
  dataSet = [];
  testboardstands = [];
  matlstands = [];
  specimennos = [];
  specimennoandprodnos = [];
  recomtestno:string;
  date = new Date();
  prodnos = [];
  matls = [];
  stands = [];
  public specs = [];//规格选项

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
  getYearMonth(){
    return (this.date.getFullYear().toString().slice(2)) + ("0" + (this.date.getMonth() + 1)).slice(-2);
  }


  ngOnInit(): void {
    this.productTestBoardDataRegistrationService.getspecimenno().subscribe((res)=>{
      if(res['result'] == "success"){
        this.specimennoandprodnos = res['data'];
        for(let item of this.specimennoandprodnos){
          if(this.prodnos.indexOf(item.prodno) == -1)
            this.prodnos.push(item.prodno)
          if(this.specimennos.indexOf(item.specimennno) == -1)
            this.specimennos.push(item.specimennno)
        }
      }
    })
    this.productTestBoardDataRegistrationService.getputmaterial().subscribe(res=>{
      if(res['result'] == 'success'){
        this.matls = res['data']['designation'];
      }
    })
    this.productTestBoardDataRegistrationService.gettestboardstand().subscribe(res=>{
      if(res['result'] == "success"){
        this.testboardstands = res['data'];
      }
    })
    this.validateForm = this.fb.group({
      prodno:[null, [Validators.required]],
      yearmonth:[this.getYearMonth(), [Validators.required,Validators.pattern(/^\d\d(0[1-9]|1[0-2])$/)]],
      testno:[null, [Validators.required,Validators.maxLength(3),Validators.minLength(3)]],
      specimenno:[null, [Validators.required]],
      specimentype:[null, [Validators.required]],
      specimenmatl:[null, [Validators.required]],
      specimenspec:[null, [Validators.required]],
      parentmatltand:[null, [Validators.required]],
      judgestand:[null, [Validators.required]],
      testdate:[null, [Validators.required]],
      a:[null, [Validators.pattern(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/)]],
      b:[null, [Validators.pattern(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/)]],
      so:[null, [Validators.pattern(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/)]],
      f02:[null],
      f1:[null],
      f02mpa:[null, [Validators.pattern("^\\d+$")]],
      f1mpa:[null, [Validators.pattern("^\\d+$")]],
      fm:[null, [Validators.pattern(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/)]],
      rm:[null, [Validators.pattern("^\\d+$")]],
      lo:[null, [Validators.pattern("^\\d+$")]],
      lu:[null, [Validators.pattern("^\\d+$")]],
      apercent:[null],
      fractposit:["焊缝"],
      hardness1:[null],
      hardness2:[null],
      hardness3:[null],
      bendangle:[180],
      bendaxdia:[null],
      bendatype:[null],
      surfacebending1:["合格"],
      backbending1:["合格"],
      surfacebending2:[null],
      backbending2:[null],
      w1:[null],
      lew1:[null],
      w2:[null],
      lew2:[null],
      w3:[null],
      lew3:[null],
      h1:[null],
      leh1:[null],
      h2:[null],
      leh2:[null],
      h3:[null],
      leh3:[null],
      gapType:[null],
      shocktemp:[null],
      user:[this._storage.get('username'), [Validators.required]],
    });
    this.productTestBoardDataRegistrationService.gettestno(null).subscribe(res=>{
      if(res['result'] == 'success'){
        this.validateForm.controls['testno'].setValue(res['testno'].substring(4, 7));
        this.recomtestno = res['testno'].substring(4, 7);
      }
    })
    this.testboardstandValidateForm = this.fb.group({
      "testboardstand":[null, [Validators.required]]
    })
  }
  formatInDate(){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.value.testdate)){
      this.validateForm.controls["testdate"].setValue(new Date().getFullYear()+"-"+this.validateForm.value.testdate);
    }else if(!yearMonthDay.test(this.validateForm.value.testdate)){
      this.validateForm.controls["testdate"].setValue(null);
    }
  }
  calcSo(){
    let a = parseFloat(this.validateForm.value.a);
    let b = parseFloat(this.validateForm.value.b);
    if(!isNaN(a*b))
      this.validateForm.controls['so'].setValue((a*b).toFixed(2));
    else
      this.validateForm.controls['so'].setValue("请检查输入是否有误!");
  }
  formatterA = (value: number) => value?`${value} a`:null;
  parserA = (value: string) => value.replace(' a', '');

  setProdno(){
    this.prodnos = [];
    for(let item of this.specimennoandprodnos){
      if(item.specimennno == this.validateForm.value.specimenno && this.prodnos.indexOf(item.prodno==-1))
        this.prodnos.push(item.prodno);
    }
    if(this.validateForm.value.specimenno && this.validateForm.value.prodno)
      this.productTestBoardDataRegistrationService.searchprotestboardcom(this.validateForm.value.prodno).subscribe(res=>{
        if(res['result'] == "success"){
          let data = res['data'].filter(item=>item.specimenno == this.validateForm.value.specimenno)[0];
          this.validateForm.controls['specimenmatl'].setValue(data.designation);
          this.validateForm.controls['specimenspec'].setValue(data.spec);
        }
      })
  }

  setSpecimenno(){
    this.specimennos = [];
    for(let item of this.specimennoandprodnos){
      if(item.prodno == this.validateForm.value.prodno && this.specimennos.indexOf(item.specimennno==-1))
        this.specimennos.push(item.specimennno);
    }
    if(this.validateForm.value.specimenno && this.validateForm.value.prodno)
      this.productTestBoardDataRegistrationService.searchprotestboardcom(this.validateForm.value.prodno).subscribe(res=>{
        if(res['result'] == "success"){
          let data = res['data'].filter(item=>item.specimenno == this.validateForm.value.specimenno)[0];
          this.validateForm.controls['specimenmatl'].setValue(data.designation);
          this.validateForm.controls['specimenspec'].setValue(data.spec);
        }
      })
  }

  checktestno(e?: MouseEvent){
    if (e) {
      e.preventDefault();
    }
    this.productTestBoardDataRegistrationService.gettestno(this.getYearMonth() + this.validateForm.value.testno).subscribe(res=>{
      if(res['result'] == 'success'){
        if(res['status'] == 2){ //不可用
          this.recomtestno = res['testno'].substring(4, 7);
          this.validateForm.controls['testno'].reset();
          this.validateForm.controls['testno'].markAsDirty();
        }
      }
    })
  }
  calcAPercent(){
    let lo = this.validateForm.value.lo;
    let lu = this.validateForm.value.lu;
    let res = (lu - lo) / lo * 100;
    if(isNumber(res))
      this.validateForm.controls['apercent'].setValue(res.toFixed(2));
    else{
      this.validateForm.controls['apercent'].setValue(null);
    }
  }
  constructor(public productTestBoardDataRegistrationService: ProductTestBoardDataRegistrationService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  calc1(controlname){//整数2舍3入，7上8下
    let num = this.validateForm.value[controlname];
    if(isNaN(num)){
      this.validateForm.controls[controlname].setValue(null);
      return;
    }
    let numc = num%10;
    if(numc>=8){
      num+=(10 - numc);
    }else if(numc>5){
      num-=(numc-5);
    }else if(numc>3){
      num+=(5 - numc);
    }else{
      num-=(numc)
    }
    this.validateForm.controls[controlname].setValue(num);
  }
  calc2(controlname){//整数2舍3入，7上8下
    let num = this.validateForm.value[controlname];
    if(isNaN(num)){
      this.validateForm.controls[controlname].setValue(null);
      return;
    }
    num*=10;
    let numc = num%10;
    if(numc>=8){
      num+=(10 - numc);
    }else if(numc>5){
      num-=(numc-5);
    }else if(numc>3){
      num+=(5 - numc);
    }else{
      num-=(numc)
    }
    this.validateForm.controls[controlname].setValue(num);
  }

  public tplModal: NzModalRef;
  public tplModalButtonLoading = false;

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

  formatEntrustdate(controlname){//日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.value[controlname])){
      this.validateForm.controls[controlname].setValue(new Date().getFullYear()+"-"+this.validateForm.value[controlname]);
    }else if(!yearMonthDay.test(this.validateForm.value.entrustdate)){
      this.validateForm.controls[controlname].setValue(null);
    }
  }
  testno = null;
  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.testno = this.validateForm.value.testno;
      this.validateForm.controls['testno'].setValue(this.validateForm.value.yearmonth+this.testno);
      this.productTestBoardDataRegistrationService.puttestboardparam(this.validateForm.value).subscribe((res)=>{
        this.validateForm.controls['testno'].setValue(this.testno);
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
        }else{
          this.modalService.error({
            nzTitle: '失败',
            nzContent: '请稍后重试！'
          })
        }
      })
    }
  }
  getMatlstand(){
    if(this.validateForm.value.specimenmatl!=null){
      this.matlstands = [];
      this.validateForm.controls['parentmatltand'].reset();
      this.productTestBoardDataRegistrationService.getmatlstandbydesignation(this.validateForm.value.specimenmatl).subscribe(res=>{
        if(res['result']=="success"){
          this.matlstands = res['matlstand'];
        }
      })
    }
  }
}
