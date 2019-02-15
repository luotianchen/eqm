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
  public thisyear = new Date().getFullYear;
  public validateForm: FormGroup;
  public validateForm2: FormGroup;
  matlType = {
    1:1,
    2:1,
    3:2,
    4:2,
    5:3
  }; // 1/2/3/4/5分别对应板材、管件、法兰、封头、焊材5种材料，后面的1、2、3为对应的模式，每一种模式对应种报表央视
  mode = 0;
  constructor(public sanitizer: DomSanitizer,public warehouseEntryNoticeService: WarehouseEntryNoticeService,public fb: FormBuilder,public msg:NzMessageService) {
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
    this.warehouseEntryNoticeService.getMatlTypeByCodedmarking(this.validateForm2.value.codedmarking).subscribe(res=>{
      if(res['result']=='success'){
        if(res['data']==0){
          this.msg.error("您输入的材料不属于板材、管件、法兰、封头、焊材！")
          return;
        }else{
          this.mode = this.matlType[res['data']];
        }
      }
    })
    if(this.validateForm2.valid) {
        this.warehouseEntryNoticeService.getReport(this.validateForm2.value.codedmarking,null,null,null).subscribe((res)=>{
          if(res["result"]=="success"){
            this.printCSS = ['assets/css/warehouseEntryNotice'+this.mode+'.css'];
            this.dataSet = res["data"];
            for(let item of this.dataSet){
              this.warehouseEntryNoticeService.getSignImage(item.user).then((res)=>{
                item.usersign = res["url"];
              }).catch(err=>{
                item.usersign = null;
              });
              this.warehouseEntryNoticeService.getSignImage(item.audit_user).then((res)=>{
                item.audit_usersign = res["url"];
              }).catch(err=>{
                item.audit_usersign = null;
              })
            }
          }else{
            this.msg.error("查询通知单失败，请核对输入信息后重新查询！");
          }
        })
    }
  }
  searchDataByMatlcode(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    this.warehouseEntryNoticeService.getMatlTypeByMatlcode(this.validateForm.value.matlcode).subscribe(res=>{
      if(res['result']=="success"){
        if(res['data']==0){
          this.msg.error("您输入的材料不属于板材、管件、法兰、封头、焊材！");
          return;
        }else{
          this.mode = this.matlType[res['data']];
          if(this.validateForm.valid) {
            this.warehouseEntryNoticeService.getReport(null,this.validateForm.value.year,this.validateForm.value.month,this.validateForm.value.matlcode).subscribe((res)=>{
              if(res["result"]=="success"){
                this.printCSS = ['assets/css/warehouseEntryNotice'+this.mode+'.css'];
                this.dataSet = res["data"];
                for(let item of this.dataSet){
                  this.warehouseEntryNoticeService.getSignImage(item.user).then((res)=>{
                    item.usersign = res["url"];
                  }).catch(err=>{
                    item.usersign = null;
                  });
                  this.warehouseEntryNoticeService.getSignImage(item.audit_user).then((res)=>{
                    item.audit_usersign = res["url"];
                  }).catch(err=>{
                    item.audit_usersign = null;
                  });
                }
              }else{
                this.msg.error("查询通知单失败，请核对输入信息后重新查询！");
              }
            })
          }
        }
      }
    })
  }
  showDate(indate){
    return indate.split('-')[0]+"年"+indate.split('-')[1]+"月"+indate.split('-')[2]+"日";
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
