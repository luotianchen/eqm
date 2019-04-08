import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {HydraulicTestReportService} from "./hydraulicTestReport.service";

@Component({
  selector: 'app-HydraulicTestReport',
  templateUrl: 'hydraulicTestReport.component.html',
  styleUrls: ['./hydraulicTestReport.component.less'],
  providers: [HydraulicTestReportService]
})
export class HydraulicTestReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public loading = false;
  status = false;
  names = [];
  constructor(public hydraulicTestReportService:HydraulicTestReportService,public fb: FormBuilder,private msg:NzMessageService){
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
      this.hydraulicTestReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+'+'+this.validateForm.value.name+"+液压试验报告"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
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
    this.hydraulicTestReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "name":[null, [Validators.required]]
    });
  }
  getNames(){
    this.hydraulicTestReportService.searchbyprodno(this.validateForm.value.prodno).subscribe(res=>{
      if(res['result'] == "success"){
        this.hydraulicTestReportService.searchchanneldata(res['dwgno']).subscribe(res=>{
          if(res['result'] == "success")
            this.names = res['data'];
        })
      }
    })
  }
}
