import {Component, OnInit} from '@angular/core';
import {WarehouseEntryNoticeService} from './warehouseEntryNotice.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-warehouseEntryNotice',
  templateUrl: 'warehouseEntryNotice.component.html',
  styleUrls: ['./warehouseEntryNotice.component.less'],
  providers: [WarehouseEntryNoticeService]
})
export class WarehouseEntryNoticeComponent implements OnInit {

  private validateForm: FormGroup;
  private validateForm2: FormGroup;
  matlType = {
    1:1,
    2:1,
    7:2,
    9:2,
    5:3
  };
  mode = null;
  constructor(private sanitizer: DomSanitizer,private warehouseEntryNoticeService: WarehouseEntryNoticeService,private fb: FormBuilder,private msg:NzMessageService) {
    this.printStyle =
      `
      th, td {
        color: black !important;
     }
     `;
  }
  dataSet = [];
  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "matlcode":[null, [Validators.required]],
      "year":[null, [Validators.required]],
      "month":[null, [Validators.required]],
    });
    this.validateForm2 = this.validateForm2 = this.fb.group({
      "codedmarking":[null, [Validators.required]]
    });
  }

  searchDataByCodedmarking(): void {
    for (const i in this.validateForm2.controls) {
      this.validateForm2.controls[ i ].markAsDirty();
      this.validateForm2.controls[ i ].updateValueAndValidity();
    }
    if(this.matlType[this.validateForm2.value.codedmarking[4]]==null){
      this.msg.error("您输入的材料不属于板材、管件、法兰、封头、焊材！")
      return;
    }else{
      this.mode = this.matlType[this.validateForm2.value.codedmarking[4]];
    }
    if(this.validateForm2.valid) {
        this.warehouseEntryNoticeService.getReport(this.validateForm2.value.codedmarking,null,null,null).subscribe((res)=>{
          if(res["result"]=="success"){
            this.dataSet = res["data"];
            for(let item of this.dataSet){
              this.warehouseEntryNoticeService.getSignImage(item.user).subscribe((res)=>{
                item.user = res["url"];
              })
              this.warehouseEntryNoticeService.getSignImage(item.audit_user).subscribe((res)=>{
                item.audit_user = res["url"];
              })
            }
          }
        })
    }
  }
  searchDataByMatlcode(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.matlType[this.validateForm.value.matlcode]==null){
      this.msg.error("您输入的材料不属于板材、管件、法兰、封头、焊材！")
      return;
    }else{
      this.mode = this.matlType[this.validateForm.value.matlcode];
    }
    if(this.validateForm.valid) {
      this.warehouseEntryNoticeService.getReport(null,this.validateForm.value.year,this.validateForm.value.month,this.validateForm.value.matlcode).subscribe((res)=>{
        if(res["result"]=="success"){
          this.dataSet = res["data"];
          for(let item of this.dataSet){
            this.warehouseEntryNoticeService.getSignImage(item.user).subscribe((res)=>{
              item.user = res["url"];
            })
            this.warehouseEntryNoticeService.getSignImage(item.audit_user).subscribe((res)=>{
              item.audit_user = res["url"];
            })
          }
        }
      })
    }
  }
  printCSS: string[];
  printStyle: string;
  printBtnBoolean = true;
  printComplete() {
    this.printBtnBoolean = true;
  }
  beforePrint() {
    this.printBtnBoolean = false;
  }
}
