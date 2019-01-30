import {Component, OnInit} from '@angular/core';
import {DesignAuditService} from './designAudit.service';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';

@Component({
  selector: 'app-designAudit',
  templateUrl: 'designAudit.component.html',
  styleUrls: ['./designAudit.component.less'],
  providers: [DesignAuditService]
})
export class DesignAuditComponent implements OnInit {
  public dataSet:any;
  placement = 'left';
  public modelData = {
    data:{},
    channel:[],
    saferel:[]
  };
  loading = true;
  constructor(public designAuditService:DesignAuditService,public message : NzMessageService,public _storage:SessionStorageService){
  }
  ngOnInit(): void {
    this.searchData();
    this.loading = false;
  }
  searchData(){
    this.designAuditService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
      }
    })
  }
  Audit(dwgno,result){
    this.designAuditService.audit(dwgno,result,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
  visible = false;

  open(dwgno): void {
    this.designAuditService.getbydwgno(dwgno).subscribe((res)=>{
      this.modelData.data = res["data"];
    });
    this.visible = true;
    this.designAuditService.getsaferel(dwgno).subscribe((res)=>{
      this.modelData.saferel = res["data"];
    });
    this.designAuditService.getchannel(dwgno).subscribe((res)=>{
      this.modelData.channel = res["data"];
    });
  }

  close(): void {
    this.visible = false;
  }
}
