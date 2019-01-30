import {Component, OnInit} from '@angular/core';
import {ProductTestBoardDataRegistrationAuditService} from './productTestBoardDataRegistrationAudit.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardDataRegistrationAudit',
  templateUrl: 'productTestBoardDataRegistrationAudit.component.html',
  styleUrls: ['./productTestBoardDataRegistrationAudit.component.less'],
  providers: [ProductTestBoardDataRegistrationAuditService]
})
export class ProductTestBoardDataRegistrationAuditComponent implements OnInit {
  constructor(public productTestBoardDataRegistrationAuditService: ProductTestBoardDataRegistrationAuditService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = []
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.productTestBoardDataRegistrationAuditService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
  audit(prodno,status){
    this.productTestBoardDataRegistrationAuditService.Audit(prodno,status,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
      }
    });
    this.searchData();
  }
}
