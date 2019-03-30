import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CleaningInsReportService} from "./cleaningInsReport.service";
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-cleaningInsReport',
  templateUrl: 'CleaningInsReport.component.html',
  styleUrls: ['./CleaningInsReport.component.less'],
  providers: [CleaningInsReportService]
})
export class CleaningInsReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public loading = false;
  status = false;
  names = [];
  cleannames = [
    "普通",
    "去油脱脂",
    "酸洗钝化",
    "特殊要求",
    "蓝点法要求"
  ]
  constructor(public cleaningInsReportService:CleaningInsReportService,public fb: FormBuilder,private msg:NzMessageService,private _storage:SessionStorageService){
  }
  submitForm(): void {
    // tslint:disable-next-line:no-any
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.loading = true;
      const formData = new FormData();
      formData.append('prodno', this.validateForm.value.prodno);
      formData.append('name', this.validateForm.value.name);
      formData.append('username', this._storage.get("username"));
      formData.append('date', this.validateForm.value.date);
      formData.append('cleanname',this.validateForm.value.cleanname);
      this.cleaningInsReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+'+'+this.validateForm.value.name+"+清洁度检查报告"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
        a.click();
        this.loading = false;
        URL.revokeObjectURL(objectUrl);
      },err=>{
        this.loading = false;
        this.msg.error("出现异常，请稍后重试！")
      })
    }
  }
  ngOnInit(): void {
    this.cleaningInsReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "name":[null, [Validators.required]],
      "cleanname":[null, [Validators.required]],
      "date":[null, [Validators.required]]
    });
  }
  formatInDate(control){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
  getNames(){
    this.cleaningInsReportService.searchbyprodno(this.validateForm.value.prodno).subscribe(res=>{
      if(res['result'] == "success"){
        this.cleaningInsReportService.searchchanneldata(res['dwgno']).subscribe(res=>{
          if(res['result'] == "success")
            this.names = res['data'];
        })
      }
    })
  }
}
