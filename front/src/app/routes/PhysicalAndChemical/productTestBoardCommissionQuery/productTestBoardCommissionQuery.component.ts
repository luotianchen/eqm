import {Component, OnInit} from '@angular/core';
import {ProductTestBoardCommissionQueryService} from './productTestBoardCommissionQuery.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardCommissionQuery',
  templateUrl: 'productTestBoardCommissionQuery.component.html',
  styleUrls: ['./productTestBoardCommissionQuery.component.less'],
  providers: [ProductTestBoardCommissionQueryService]
})
export class ProductTestBoardCommissionQueryComponent implements OnInit {
  constructor(private productTestBoardCommissionQueryService: ProductTestBoardCommissionQueryService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }
  dataSet = [];
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.productTestBoardCommissionQueryService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
}
