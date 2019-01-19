import {Component, OnInit} from '@angular/core';
import {PressurePartsAuditService} from "./pressurePartsAudit.service";
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';

@Component({
  selector: 'app-pressurePartsAudit',
  templateUrl: 'pressurePartsAudit.component.html',
  styleUrls: ['./pressurePartsAudit.component.less'],
  providers: [PressurePartsAuditService]
})
export class PressurePartsAuditComponent implements OnInit {
  private dataSet:any;
  private dataDetail = {};
  constructor(private pressurePartsAuditService:PressurePartsAuditService,private message : NzMessageService,private _storage:SessionStorageService){
  }
  search(audit){
    this.pressurePartsAuditService.getdistribute(audit).subscribe((response)=>{
      if(response['result'] == "success"){
        this.dataDetail[audit] = response['data'];
      }
    })
  }
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.pressurePartsAuditService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        console.log(this.dataSet);
      }
    })
  }
  Audit(audit,result){
    this.pressurePartsAuditService.audit(audit,result,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
}
