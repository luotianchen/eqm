import {Component, OnInit} from '@angular/core';
import {MaterialReinspectionCommissionAuditService} from './materialReinspectionCommissionAudit.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialReinspectionCommissionAudit',
  templateUrl: 'materialReinspectionCommissionAudit.component.html',
  styleUrls: ['./materialReinspectionCommissionAudit.component.less'],
  providers: [MaterialReinspectionCommissionAuditService]
})
export class MaterialReinspectionCommissionAuditComponent implements OnInit {
  constructor(public materialReinspectionCommissionAuditService: MaterialReinspectionCommissionAuditService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = []
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.materialReinspectionCommissionAuditService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
  audit(prodno,status){
    this.materialReinspectionCommissionAuditService.Audit(prodno,status,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
      }
    });
    this.searchData();
  }
}
