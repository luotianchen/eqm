import {Component, OnInit} from '@angular/core';
import {pressTestReportService} from './pressTestReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-pressTestReport',
  templateUrl: 'pressTestReport.component.html',
  styleUrls: ['./pressTestReport.component.less'],
  providers: [pressTestReportService]
})
export class PressTestReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public pdfSrc = null;
  public loading = false;
  status = false;
  objectUrl = null;
  constructor(public pressTestReportService:pressTestReportService,public fb: FormBuilder,private sanitizer: DomSanitizer){
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
      this.pressTestReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        this.pdfSrc = new Uint8Array(res);
        let blob = new Blob([res], {type: 'application/pdf'});
        this.objectUrl = this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
        this.loading = false;
        this.status = true;
      })
    }
  }
  ngOnInit(): void {
    this.pressTestReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
  }
}
