import {Component, OnInit} from '@angular/core';
import {VacuumTestReportService} from './vacuumTestReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-vacuumTestReportReport',
  templateUrl: 'vacuumTestReport.component.html',
  styleUrls: ['./vacuumTestReport.component.less'],
  providers: [VacuumTestReportService]
})
export class VacuumTestReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public loading = false;
  status = false;
  constructor(public vacuumTestReportService:VacuumTestReportService,public fb: FormBuilder,private msg:NzMessageService){
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
      this.vacuumTestReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+"+真空考核报告"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
        a.click();
        URL.revokeObjectURL(objectUrl);
        this.loading = false;
      },err=>{
        this.loading = false;
        this.msg.error("出现异常，请稍后重试！")
      })
    }
  }
  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.vacuumTestReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
  }
}
