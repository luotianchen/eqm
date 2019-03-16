import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SessionStorageService} from '../../../core/storage/storage.service';
import {NzMessageService, NzModalRef, NzModalService} from 'ng-zorro-antd';
import {MeasuringInstrumentLedgerService} from './measuringInstrumentLedger.service';

@Component({
  selector: 'app-measuringInstrumentLedger',
  templateUrl: 'measuringInstrumentLedger.component.html',
  styleUrls: ['./measuringInstrumentLedger.component.less'],
  providers: [MeasuringInstrumentLedgerService]
})
export class MeasuringInstrumentLedgerComponent implements OnInit {
  validateForm: FormGroup;
  gaugenames = [];
  millunits = [];
  managlevels = [];
  userform = [];
  public notes = []; //备注选项
  constructor(public fb: FormBuilder, public measuringInstrumentLedgerService: MeasuringInstrumentLedgerService,public _storage: SessionStorageService,public msg:NzMessageService,public modalService: NzModalService) {
  }
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      user:[this._storage.get("username")],
      gaugename:[null,[Validators.required]],
      gaugeno:[null,[Validators.required]],
      exitno:[null,[Validators.required]],
      type:[null,[Validators.required]],
      measrangemin:[0,[Validators.required]],
      measrangemax:[null,[Validators.required]],
      accuclass:[null,[Validators.required]],
      millunit:[null,[Validators.required]],
      exitdate:[null,[Validators.required]],
      managlevel:[null,[Validators.required]],
      calibdate:[null,[Validators.required]],
      recalibdate:[null,[Validators.required]],
      specialist:[null,[Validators.required]],
      calibinterval:[null,[Validators.required]],
      note:[null],
    });
    this.measuringInstrumentLedgerService.getGaugename().subscribe((res)=>{
      if(res["result"]=="success"){
        this.gaugenames = res["data"];
      }
    })
    this.measuringInstrumentLedgerService.getMillunit().subscribe((res)=>{
      if(res['result']=='success'){
        this.millunits = res['data']['millunit'];
      }
    })
    this.measuringInstrumentLedgerService.getManaglevel().subscribe((res)=>{
      if(res['result']=="success"){
        this.managlevels = res['data'];
      }
    })
    this.measuringInstrumentLedgerService.getUsers().subscribe((res)=>{
      if(res['result']=="success"){
        this.userform = res['data'];
      }
    })
  }

  formatInDate(control){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
  getCycle(){
    if(this.validateForm.value.gaugename!=null && this.validateForm.value.managlevel!=null &&this.validateForm.value.gaugename!="" && this.validateForm.value.managlevel!=""){
      this.measuringInstrumentLedgerService.getCircle(this.validateForm.value.gaugename,this.validateForm.value.managlevel).subscribe((res)=>{
        if(res['result']=="success"){
          this.validateForm.controls['calibinterval'].setValue(res['data']);
          this.nextdate();
        }
      })
    }
  }
  nextdate(){
    if(this.validateForm.value.calibdate!=null && this.validateForm.value.calibinterval!=null && this.validateForm.value.calibdate!="" && this.validateForm.value.calibinterval!=""){
      if(this.validateForm.value.calibinterval.indexOf("一次性")!=-1){
        this.validateForm.controls['recalibdate'].setValue('无');
      }else if(this.validateForm.value.calibinterval.indexOf("天")!=-1){
        let date = new Date(this.validateForm.value.calibdate);
        let newDate = this.DateAdd("d",parseInt(this.validateForm.value.calibinterval.substring(0,this.validateForm.value.calibinterval.length-1)),date);
        this.validateForm.controls['recalibdate'].setValue(newDate.getFullYear()+"-"+(newDate.getMonth()+1)+"-"+newDate.getDate());
      }else if(this.validateForm.value.calibinterval.indexOf("月")!=-1){
        let date = new Date(this.validateForm.value.calibdate);
        let newDate = this.DateAdd("m",parseInt(this.validateForm.value.calibinterval.substring(0,this.validateForm.value.calibinterval.length-1)),date);
        this.validateForm.controls['recalibdate'].setValue(newDate.getFullYear()+"-"+(newDate.getMonth()+1)+"-"+newDate.getDate());
      }
    }
  }
  dataSet = [];

  /*
  *   功能:实现VBScript的DateAdd功能.
  *   参数:interval,字符串表达式，表示要添加的时间间隔.
  *   参数:number,数值表达式，表示要添加的时间间隔的个数.
  *   参数:date,时间对象.
  *   返回:新的时间对象.
  *   var now = new Date();
  *   var newDate = DateAdd( "d", 5, now);
  *---------------   DateAdd(interval,number,date)   -----------------
  */
  DateAdd(interval, number, date) {
    switch (interval) {
      case "y": {
        date.setFullYear(date.getFullYear() + number);
        return date;
        break;
      }
      case "q": {
        date.setMonth(date.getMonth() + number * 3);
        return date;
        break;
      }
      case "m": {
        date.setMonth(date.getMonth() + number);
        return date;
        break;
      }
      case "w": {
        date.setDate(date.getDate() + number * 7);
        return date;
        break;
      }
      case "d": {
        date.setDate(date.getDate() + number);
        return date;
        break;
      }
      case "h": {
        date.setHours(date.getHours() + number);
        return date;
        break;
      }
      case "m": {
        date.setMinutes(date.getMinutes() + number);
        return date;
        break;
      }
      case "s": {
        date.setSeconds(date.getSeconds() + number);
        return date;
        break;
      }
      default: {
        date.setDate(date.getDate() + number);
        return date;
        break;
      }
    }
  }
  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      console.log(this.validateForm.value);
      this.measuringInstrumentLedgerService.putMeasuringInstrumentLedger(this.validateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.msg.success("提交成功！");
        }
      })
    }
  }
  // getInfoByGaugeno(){
  //   if(this.validateForm.value.gaugeno!=null&&this.validateForm.value.gaugeno!="")
  //     this.measuringInstrumentLedgerService.getInfo(this.validateForm.value.gaugeno).subscribe((res)=>{
  //       if(res['result']=="success"){
  //         this.validateForm = this.fb.group({
  //           user:[this._storage.get("username")],
  //           gaugename:[res['data'][0].gaugename,[Validators.required]],
  //           gaugeno:[res['data'][0].gaugeno,[Validators.required]],
  //           exitno:[res['data'][0].exitno,[Validators.required]],
  //           type:[res['data'][0].type,[Validators.required]],
  //           measrangemin:[res['data'][0].measrangemin,[Validators.required]],
  //           measrangemax:[res['data'][0].measrangemax,[Validators.required]],
  //           accuclass:[res['data'][0].accuclass,[Validators.required]],
  //           millunit:[res['data'][0].millunit,[Validators.required]],
  //           exitdate:[res['data'][0].exitdate,[Validators.required]],
  //           managlevel:[res['data'][0].managlevel,[Validators.required]],
  //           calibdate:[res['data'][0].calibdate,[Validators.required]],
  //           recalibdate:[res['data'][0].recalibdate,[Validators.required]],
  //           specialist:[res['data'][0].specialist,[Validators.required]],
  //           calibinterval:[res['data'][0].calibinterval,[Validators.required]],
  //           note:[res['data'][0].note],
  //         });
  //         this.validateForm.controls['calibdate'].setValue(null);
  //         this.validateForm.setControl('exitno',new FormControl({value: res['data'][0].exitno, disabled: true}));
  //         this.msg.success("已恢复数据");
  //       }else{
  //         this.msg.success("未查询到该条记录！");
  //         this.validateForm.setControl('exitno',new FormControl({value: null, disabled: false}));
  //       }
  //     })
  //   else
  //     this.validateForm.setControl('exitno',new FormControl({value: null, disabled: false}));
  // }
}
