import {Component, OnInit} from '@angular/core';
import {TestParametersAuditService} from './testParametersAudit.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-testParametersAudit',
  templateUrl: 'testParametersAudit.component.html',
  styleUrls: ['./testParametersAudit.component.less'],
  providers: [TestParametersAuditService]
})
export class TestParametersAuditComponent implements OnInit {
  constructor(private testParametersAuditService: TestParametersAuditService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }
  dataSet = []
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.testParametersAuditService.getPressureTest().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
      }
    })
  }
  audit(prodno,ppart,status){
    this.testParametersAuditService.Audit(prodno,ppart,status,this._storage.get('username')).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
      }
    });
    this.searchData();
  }
}
