import {Component, OnInit} from '@angular/core';
import {ProductTestBoardCommissionAuditService} from './productTestBoardCommissionAudit.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardCommissionAudit',
  templateUrl: 'productTestBoardCommissionAudit.component.html',
  styleUrls: ['./productTestBoardCommissionAudit.component.less'],
  providers: [ProductTestBoardCommissionAuditService]
})
export class ProductTestBoardCommissionAuditComponent implements OnInit {
  constructor(public productTestBoardCommissionAuditService: ProductTestBoardCommissionAuditService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = []
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.productTestBoardCommissionAuditService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
  audit(id,prodno,specimenno,status){
    this.productTestBoardCommissionAuditService.Audit(id,prodno,specimenno,status,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
      }
    });
    this.searchData();
  }
}
