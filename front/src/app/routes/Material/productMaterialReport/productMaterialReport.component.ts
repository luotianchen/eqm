import {Component, OnInit} from '@angular/core';
import {ProductMaterialReportService} from './productMaterialReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DomSanitizer} from "@angular/platform-browser";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-productMaterialreport',
  templateUrl: 'productMaterialReport.component.html',
  styleUrls: ['./productMaterialReport.component.less'],
  providers: [ProductMaterialReportService]
})
export class ProductMaterialReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public pdfSrc = null;
  public loading = false;
  status = false;
  objectUrl = null;
  constructor(public productMaterialReportService:ProductMaterialReportService,public fb: FormBuilder,private sanitizer: DomSanitizer,private msg:NzMessageService){
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
      this.productMaterialReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
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
  ngOnInit(): void {
    this.productMaterialReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
  }
}
