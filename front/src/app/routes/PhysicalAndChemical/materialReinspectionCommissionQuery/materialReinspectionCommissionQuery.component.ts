import {Component, OnInit} from '@angular/core';
import {MaterialReinspectionCommissionQueryService} from './materialReinspectionCommissionQuery.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialReinspectionCommissionQuery',
  templateUrl: 'materialReinspectionCommissionQuery.component.html',
  styleUrls: ['./materialReinspectionCommissionQuery.component.less'],
  providers: [MaterialReinspectionCommissionQueryService]
})
export class MaterialReinspectionCommissionQueryComponent implements OnInit {
  constructor(public materialReinspectionCommissionQueryService: MaterialReinspectionCommissionQueryService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = [];
  audited = {};
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.materialReinspectionCommissionQueryService.searchrematerial().subscribe(res=>{
      if(res['result']=='success'){
        for(let item of res['data']){
          this.audited['a'+item['codedmarking']] = true;
        }
      }
    });
    this.materialReinspectionCommissionQueryService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data2 of this.dataSet){
          data2.expand = true;
        }
      }
    })
  }
}
