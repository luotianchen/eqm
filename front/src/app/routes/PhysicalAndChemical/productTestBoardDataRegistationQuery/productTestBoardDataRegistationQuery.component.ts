import {Component, OnInit} from '@angular/core';
import {ProductTestBoardDataRegistationQueryService} from './productTestBoardDataRegistationQuery.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardDataRegistationQuery',
  templateUrl: 'productTestBoardDataRegistationQuery.component.html',
  styleUrls: ['./productTestBoardDataRegistationQuery.component.less'],
  providers: [ProductTestBoardDataRegistationQueryService]
})
export class ProductTestBoardDataRegistationQueryComponent implements OnInit {
  constructor(private productTestBoardDataRegistationQueryService: ProductTestBoardDataRegistationQueryService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }
  dataSet = [];
  ngOnInit(): void {
    this.searchData();
  }
  searchData(){
    this.productTestBoardDataRegistationQueryService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = true;
        }
      }
    })
  }
}
