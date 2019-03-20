import {Component, OnInit} from '@angular/core';
import {ProductTestBoardDataRegistrationQueryService} from './productTestBoardDataRegistrationQuery.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardDataRegistrationQuery',
  templateUrl: 'productTestBoardDataRegistrationQuery.component.html',
  styleUrls: ['./productTestBoardDataRegistrationQuery.component.less'],
  providers: [ProductTestBoardDataRegistrationQueryService]
})
export class ProductTestBoardDataRegistrationQueryComponent implements OnInit {
  constructor(public productTestBoardDataRegistrationQueryService: ProductTestBoardDataRegistrationQueryService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = []
  prodno = [];
  validateForm:FormGroup;
  ngOnInit(): void {
    this.productTestBoardDataRegistrationQueryService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodno = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null],
      "year":[new Date().getFullYear(),[Validators.maxLength(4),Validators.minLength(4),Validators.max(new Date().getFullYear()),Validators.min(1900)]]
    });
    this.searchData();
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }
  searchData(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.productTestBoardDataRegistrationQueryService.getAudit(this.validateForm.value.prodno,this.validateForm.value.year).subscribe((res)=>{
        if(res['result']=='success'){
          this.dataSet = res['data'];
        }
      })
    }
  }
}
