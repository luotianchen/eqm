import {Component, OnInit} from '@angular/core';
import {VacuumParameterAuditService} from "./vacuumParameterAudit.service";
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-vacuumParameterAudit',
  templateUrl: 'vacuumParameterAudit.component.html',
  styleUrls: ['./vacuumParameterAudit.component.less'],
  providers: [VacuumParameterAuditService]
})
export class VacuumParameterAuditComponent implements OnInit {
  private dataSet:any;
  constructor(private vacuumParameterAuditService:VacuumParameterAuditService,private message : NzMessageService,private _storage:SessionStorageService){
  }

  ngOnInit(): void {
    this.vacuumParameterAuditService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
      }
    })
  }
  audit(prodno,status){
    this.vacuumParameterAuditService.audit(prodno,this._storage.get("username"),status).subscribe((res)=>{
      if(res['result']=="success"){
        this.message.success("审核成功！");
        this.vacuumParameterAuditService.getaudit().subscribe((res)=>{
          if(res['result']=="success"){
            this.dataSet = res['data'];
          }
        })
      }
    })
  }
}
