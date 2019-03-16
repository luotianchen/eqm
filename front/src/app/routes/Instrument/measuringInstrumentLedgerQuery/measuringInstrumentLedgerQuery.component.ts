import {Component, OnInit} from '@angular/core';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {MeasuringInstrumentLedgerQueryService} from './measuringInstrumentLedgerQuery.service';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-measuringInstrumentLedgerQuery',
  templateUrl: 'measuringInstrumentLedgerQuery.component.html',
  styleUrls: ['./measuringInstrumentLedgerQuery.component.less'],
  providers: [MeasuringInstrumentLedgerQueryService]
})
export class MeasuringInstrumentLedgerQueryComponent implements OnInit {
  public dataSet:any[];
  loading = true;
  constructor(public measuringInstrumentLedgerQueryService:MeasuringInstrumentLedgerQueryService,public message : NzMessageService,public _storage:SessionStorageService,public fb:FormBuilder){
  }
  validateForm:FormGroup;
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      gaugeno:[null],
      exitno:[null],
      calibdate:[null],
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
    this.measuringInstrumentLedgerQueryService.getaudit(this.validateForm.value.gaugeno,this.validateForm.value.exitno,this.validateForm.value.calibdate).subscribe((res)=>{
      if(res['result']=="success"){
        if(reset)
          this.dataSet = res['data'].filter(item=>new Date(item.recalibdate) >= new Date())
        else
          this.dataSet = res['data'];
        for(let data of this.dataSet)
          data['expand'] = true;
        this.loading = false;
      }
    })
  }
}
