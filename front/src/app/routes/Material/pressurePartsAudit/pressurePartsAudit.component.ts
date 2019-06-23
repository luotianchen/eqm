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
  public dataSet:any = [];
  public dataDetail = {};
  username2name = {};
  users = [];
  constructor(public pressurePartsAuditService:PressurePartsAuditService,public message : NzMessageService,public _storage:SessionStorageService){
  }
  search(audit){
    this.pressurePartsAuditService.getdistribute(audit).subscribe((response)=>{
      if(response['result'] == "success"){
        this.dataDetail[audit] = response['data'];
      }
    })
  }
  ngOnInit(): void {
    this.pressurePartsAuditService.getuserform().subscribe(res=>{
      if(res['result']=='success'){
        this.users = res['data'];
        for(let user of this.users){
          this.username2name[user.username] = user.name;
        }
        this.searchData();
      }
    });
  }
  searchData(){
    this.pressurePartsAuditService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          this.search(data.audit);
        }
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
