import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {ProductManufacturingParametersAuditService} from './productManufacturingParametersAudit.service';

@Component({
  selector: 'app-productManufacturingParametersAudit',
  templateUrl: 'productManufacturingParametersAudit.component.html',
  styleUrls: ['./productManufacturingParametersAudit.component.less'],
  providers: [ProductManufacturingParametersAuditService]
})
export class ProductManufacturingParametersAuditComponent implements OnInit {
  constructor(public productManufacturingParametersAuditService: ProductManufacturingParametersAuditService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  dataSet = []
  dataSet2 = []
  ngOnInit(): void {
    this.searchData();
    this.searchData2();
  }
  searchData(){
    this.productManufacturingParametersAuditService.getAudit().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
      }
    })
  }
  searchData2(){
    this.productManufacturingParametersAuditService.getAudit2().subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet2 = res['data'];
      }
    })
  }
  parse(data){
    return JSON.parse(data)
  }
  audit(prodno,status){
    this.productManufacturingParametersAuditService.Audit(prodno,status,this._storage.get('username')).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
      }
    });
    this.searchData();
  }
  audit2(prodno,dwgno,status){
    this.productManufacturingParametersAuditService.Audit2(prodno,dwgno,status,this._storage.get('username')).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
      }
    });
    this.searchData2();
  }
}
