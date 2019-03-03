import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {WeldingRecordService} from "./weldingRecord.service";

@Component({
  selector: 'app-weldingRecord',
  templateUrl: 'weldingRecord.component.html',
  styleUrls: ['./weldingRecord.component.less'],
  providers: [WeldingRecordService]
})
export class WeldingRecordComponent implements OnInit {
  public prodnos:any;
  validateForm: FormGroup;
  dataSet = [];
  names = [];
  ngOnInit(): void {
    this.weldingRecordService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.weldingRecordService.getUserNames().subscribe(res=>{
      if(res['result'] == "success"){
        this.names = res['data'];
      }
    })
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "weldno":[null, [Validators.required]],
      "weldevano":[null, [Validators.required]],
      "weldmethod":[null, [Validators.required]],
      "usernote":[null],
      "welddate":[null],
      "inspector":[null, [Validators.required]],
      "entrustdate":[null, [Validators.required]],
      "ndtdate":[null, [Validators.required]],
      "user":[this._storage.get("username")]
    });
  }

  searchData(): void {
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.weldingRecordService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
        }
      })
    }
  }
  constructor(public weldingRecordService: WeldingRecordService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  formatInDate(control){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
  check(){
    if(this.validateForm.value.ndtdate && this.validateForm.value.entrustdate){
      let ndtdate = this.validateForm.value.ndtdate.split('-'),entrustdate = this.validateForm.value.entrustdate.split('-');
      if(ndtdate[0] < entrustdate[0]){
        this.validateForm.controls['ndtdate'].reset();
      }else if(ndtdate[1] < entrustdate[1]){
        this.validateForm.controls['ndtdate'].reset();
      }else if(ndtdate[2] < entrustdate[2]){
        this.validateForm.controls['ndtdate'].reset();
      }
    }
    if(this.validateForm.value.welddate && this.validateForm.value.entrustdate){
      let welddate = this.validateForm.value.welddate.split('-'),entrustdate = this.validateForm.value.entrustdate.split('-');
      if(welddate[0] > entrustdate[0]){
        this.validateForm.controls['entrustdate'].reset();
      }else if(welddate[1] > entrustdate[1]){
        this.validateForm.controls['entrustdate'].reset();
      }else if(welddate[2] > entrustdate[2]){
        this.validateForm.controls['entrustdate'].reset();
      }
    }
  }
  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.weldingRecordService.putWeldingRecord(this.validateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '提交成功',
            nzContent: '焊接记录提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }
}
