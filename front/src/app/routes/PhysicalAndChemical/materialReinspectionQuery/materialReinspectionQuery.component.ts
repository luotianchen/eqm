import {Component, OnInit} from '@angular/core';
import {MaterialReinspectionQueryService} from './materialReinspectionQuery.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialReinspectionQuery',
  templateUrl: 'materialReinspectionQuery.component.html',
  styleUrls: ['./materialReinspectionQuery.component.less'],
  providers: [MaterialReinspectionQueryService]
})
export class MaterialReinspectionQueryComponent implements OnInit {
  constructor(public materialReinspectionQueryService: MaterialReinspectionQueryService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  validateForm:FormGroup;
  dataSet = []
  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "year":[new Date().getFullYear()]
    });
    this.searchData();
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }

  searchData(){
    this.materialReinspectionQueryService.getAudit(this.validateForm.value.year,this.validateForm.value.codedmarking).subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
}
