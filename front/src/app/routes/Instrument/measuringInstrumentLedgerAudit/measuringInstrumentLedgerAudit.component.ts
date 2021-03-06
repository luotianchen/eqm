import {Component, OnInit} from '@angular/core';
import {MeasuringInstrumentLedgerAuditService} from './measuringInstrumentLedgerAudit.service';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';

@Component({
  selector: 'app-measuringInstrumentLedgerAudit',
  templateUrl: 'measuringInstrumentLedgerAudit.component.html',
  styleUrls: ['./measuringInstrumentLedgerAudit.component.less'],
  providers: [MeasuringInstrumentLedgerAuditService]
})
export class MeasuringInstrumentLedgerAuditComponent implements OnInit {
  public dataSet = [];
  loading = true;
  constructor(public measuringInstrumentLedgerAuditService:MeasuringInstrumentLedgerAuditService,public message : NzMessageService,public _storage:SessionStorageService){
    this.searchData();
  }
  ngOnInit(): void {
  }
  searchData(){
    this.measuringInstrumentLedgerAuditService.getaudit().subscribe((res:any)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        this.loading = false;
        for(let data of this.dataSet){
          data['expand'] = true;
        }
      }
    })
  }
  Audit(id,result){
    this.measuringInstrumentLedgerAuditService.audit(id,result,this._storage.get("username")).subscribe((res:any)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
}
