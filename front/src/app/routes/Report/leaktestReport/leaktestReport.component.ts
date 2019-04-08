import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {LeaktestReportService} from "./leaktestReport.service";

@Component({
  selector: 'app-leaktestReport',
  templateUrl: 'leaktestReport.component.html',
  styleUrls: ['./leaktestReport.component.less'],
  providers: [LeaktestReportService]
})
export class LeaktestReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public loading = false;
  status = false;
  names = [];
  constructor(public leaktestReportService:LeaktestReportService,public fb: FormBuilder,private msg:NzMessageService){
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
      this.leaktestReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+'+'+this.validateForm.value.name+"+泄漏性试验检验报告"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
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
    this.leaktestReportService.getprodno().subscribe((res) => {
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
    this.leaktestReportService.searchbyprodno(this.validateForm.value.prodno).subscribe(res=>{
      if(res['result'] == "success"){
        this.leaktestReportService.searchchanneldata(res['dwgno']).subscribe(res=>{
          if(res['result'] == "success")
            this.names = res['data'];
        })
      }
    })
  }
}
