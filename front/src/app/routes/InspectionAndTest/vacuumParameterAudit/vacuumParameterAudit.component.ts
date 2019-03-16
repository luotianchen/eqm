import {Component, OnInit} from '@angular/core';
import {VacuumParameterAuditService} from "./vacuumParameterAudit.service";
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { NgxXLSXService } from '@notadd/ngx-xlsx';
@Component({
  selector: 'app-vacuumParameterAudit',
  templateUrl: 'vacuumParameterAudit.component.html',
  styleUrls: ['./vacuumParameterAudit.component.less'],
  providers: [VacuumParameterAuditService]
})
export class VacuumParameterAuditComponent implements OnInit {
  public dataSet :Array<any>;
  status = false;
  constructor(public vacuumParameterAuditService:VacuumParameterAuditService,public message : NzMessageService,public _storage:SessionStorageService,private fb:FormBuilder,private excel:NgxXLSXService){
  }
  public prodno:any;
  headers = ['产品编号',
    '初始格数',
    '静置后格数',
    '初始压力(Pa)',
    '静置后压力(Pa)',
    '规管加热电流',
    '检测开始日期',
    '检测结束日期',
    '封口真空',
    '封口日期',
    '封口温度',
    '侧漏放气率温度',
    '真空考核操作工',
    '漏放气速率',
    '提交人',
    '审核人',
    '提交时间']
  validateForm:FormGroup;
  ngOnInit(): void {
    this.vacuumParameterAuditService.getprodno().subscribe(res=>{
      if(res['result'] == "success"){
        this.prodno = res['data']
      }
    })
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.searchData()
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }
  searchData(){
    this.vacuumParameterAuditService.getaudit(this.validateForm.value.prodno).subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        this.status = true;
      }else{
        this.status = false;
      }
    })
  }
  audit(prodno,status){
    this.vacuumParameterAuditService.audit(prodno,this._storage.get("username"),status).subscribe((res)=>{
      if(res['result']=="success"){
        this.message.success("审核成功！");
        this.searchData()
      }
    })
  }
  exportExcel(){
    this.vacuumParameterAuditService.getauditOk(this.validateForm.value.prodno).subscribe((res)=>{
      if(res['result']=="success"){
        this.excel.exportAsExcelFile(res['data'],'真空参数',this.headers)
        this.status = true;
      }else{
        this.status = false;
      }
    })
  }
}
