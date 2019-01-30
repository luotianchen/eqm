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
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.productTestBoardDataRegistrationQueryService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
}
