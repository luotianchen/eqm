import {Component, OnInit} from '@angular/core';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {MeasuringInstrumentLedgerQueryService} from './measuringInstrumentLedgerQuery.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NgxXLSXService} from "@notadd/ngx-xlsx";

@Component({
  selector: 'app-measuringInstrumentLedgerQuery',
  templateUrl: 'measuringInstrumentLedgerQuery.component.html',
  styleUrls: ['./measuringInstrumentLedgerQuery.component.less'],
  providers: [MeasuringInstrumentLedgerQueryService]
})
export class MeasuringInstrumentLedgerQueryComponent implements OnInit {
  public dataSet = [];
  loading = true;
  isVisible = false;
  loadpage = false;
  constructor(public measuringInstrumentLedgerQueryService:MeasuringInstrumentLedgerQueryService,public message : NzMessageService,public _storage:SessionStorageService,public fb:FormBuilder,private excel:NgxXLSXService){
    this.loadpage = true;
  }
  headers = [
    '名称',
    '厂内编号',
    '出厂编号',
    '型号',
    '测量范围小',
    '测量范围大',
    '精度等级',
    '生产单位',
    '出厂日期',
    '管理类别',
    '检定日期',
    '下次检定日期',
    '专管人',
    '确认间隔',
    '备注'
  ];

  validateForm:FormGroup;
  validateForm2:FormGroup;
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      gaugeno:[null],
      exitno:[null],
      calibdate:[null],
    })
    this.validateForm2 = this.fb.group({
      note:[null,[Validators.required]],
      date:[null,[Validators.required]]
    })
    this.searchData(true)
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }
  formatDate(){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.value.calibdate)){
      this.validateForm.controls["calibdate"].setValue(new Date().getFullYear()+"-"+this.validateForm.value.calibdate);
    }else if(!yearMonthDay.test(this.validateForm.value.calibdate)){
      this.validateForm.controls["calibdate"].setValue(null);
    }
  }
  searchData(reset: boolean = false){
    this.measuringInstrumentLedgerQueryService.getaudit(this.validateForm.value.gaugeno,this.validateForm.value.exitno,this.validateForm.value.calibdate).subscribe((res:any)=>{
      if(res['result']=="success"){
        if(reset)
          this.dataSet = res['data'].filter(item=>new Date(item.recalibdate) >= new Date());
        else
          this.dataSet = res['data'];
        for(let data of this.dataSet)
          data['expand'] = true;
        this.loading = false;
      }
    })
  }
  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    for (const i in this.validateForm2.controls) {
      this.validateForm2.controls[ i ].markAsDirty();
      this.validateForm2.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm2.valid){
      this.measuringInstrumentLedgerQueryService.searchledgerbynotedate(this.validateForm2.value.note,this.validateForm2.value.date).subscribe((res)=>{
        if(res['result']=="success"){
          this.excel.exportAsExcelFile(res['data'],'计量台账',this.headers);
          this.isVisible = false;
        }
      })
    }
  }
  handleCancel(){
    this.isVisible = false
  }
}
