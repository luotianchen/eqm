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
  dataSet = []
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.materialReinspectionCommissionQueryService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
}
