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
  private dataSet:any;
  loading = true;
  constructor(private measuringInstrumentLedgerAuditService:MeasuringInstrumentLedgerAuditService,private message : NzMessageService,private _storage:SessionStorageService){
  }
  ngOnInit(): void {
    this.searchData();
    this.loading = false;
  }
  searchData(){
    this.measuringInstrumentLedgerAuditService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data['expand'] = true;
        }
      }
    })
  }
  Audit(id,result){
    this.measuringInstrumentLedgerAuditService.audit(id,result,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
}
