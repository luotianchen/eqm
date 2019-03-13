import {Component, OnInit} from '@angular/core';
import {QuaPressVesPlanReportService} from './quaPressVesPlanReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UploadFile} from "ng-zorro-antd/upload";

@Component({
  selector: 'app-quaPressVesPlanReport',
  templateUrl: 'quaPressVesPlanReport.component.html',
  styleUrls: ['./quaPressVesPlanReport.component.less'],
  providers: [QuaPressVesPlanReportService]
})
export class QuaPressVesPlanReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public quaPressVesPlanReportService:QuaPressVesPlanReportService,public fb: FormBuilder){
  }
  beforeUpload = (file: UploadFile): boolean => {
    this.fileList = this.fileList.concat(file);
    return false;
  }
  uploading = false;
  fileList = [];

  handleUpload(): void {
    // tslint:disable-next-line:no-any
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      const formData = new FormData();
      formData.append('prodno', this.validateForm.value.prodno);
      formData.append('excel', this.fileList[0]);
      this.quaPressVesPlanReportService.getReport(formData).subscribe((res:any)=>{
        let blob = new Blob([res])
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', "质量计划说明"+".xls");
        a.click();
        URL.revokeObjectURL(objectUrl);
      })
    }
  }

  ngOnInit(): void {
    this.quaPressVesPlanReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
  }
}
