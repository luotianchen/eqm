import {Component, OnInit} from '@angular/core';
import {CertificateReportService} from './certificateReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-certificateReport',
  templateUrl: 'certificateReport.component.html',
  styleUrls: ['./certificateReport.component.less'],
  providers: [CertificateReportService]
})
export class CertificateReportComponent implements OnInit {
  validateForm: FormGroup;
  public codedmarkings = [];
  codedmarking = "";
  public pdfSrc = null;
  public loading = false;
  status = false;
  objectUrl = null;
  constructor(public certificateReportService:CertificateReportService,public fb: FormBuilder,private sanitizer: DomSanitizer,private msg:NzMessageService){
  }
  ngOnInit(): void {
    this.certificateReportService.getlogo().subscribe(res=>{
      if(res['result'] != "success"){alert("管理员未设置logo！");
      }
    });
    this.certificateReportService.getcodedmarking().subscribe((res) => {
      if (res["result"] == "success") {
        this.codedmarkings = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]]
    });
  }
  submitForm(): void {
    // tslint:disable-next-line:no-any
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.codedmarking = this.validateForm.value.codedmarking;
      this.loading = true;
      const formData = new FormData();
      formData.append('codedmarking', this.validateForm.value.codedmarking);
      this.certificateReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        this.pdfSrc = new Uint8Array(res);
        let blob = new Blob([res], {type: 'application/pdf'});
        this.objectUrl = this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
        this.loading = false;
        this.status = true;
      },err=>{
        this.loading = false;
        this.msg.error("出现异常，请稍后重试！")
      })
    }
  }
}
