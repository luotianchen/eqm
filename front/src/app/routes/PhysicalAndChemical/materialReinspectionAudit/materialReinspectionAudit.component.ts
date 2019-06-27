import {Component, OnInit} from '@angular/core';
import {MaterialReinspectionAuditService} from './materialReinspectionAudit.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialReinspectionAudit',
  templateUrl: 'materialReinspectionAudit.component.html',
  styleUrls: ['./materialReinspectionAudit.component.less'],
  providers: [MaterialReinspectionAuditService]
})
export class MaterialReinspectionAuditComponent implements OnInit {
  constructor(public materialReinspectionAuditService: MaterialReinspectionAuditService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = []
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.materialReinspectionAuditService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
  audit(prodno,status,num){
    this.materialReinspectionAuditService.Audit(prodno,status,num,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }
    });
  }
}
