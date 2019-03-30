import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PressVesShapeSizeInsReportService} from "./pressVesShapeSizeInsReport.service";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-pressVesShapeSizeInsReport',
  templateUrl: 'PressVesShapeSizeInsReport.component.html',
  styleUrls: ['./PressVesShapeSizeInsReport.component.less'],
  providers: [PressVesShapeSizeInsReportService]
})
export class PressVesShapeSizeInsReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public loading = false;
  status = false;
  constructor(public pressVesShapeSizeInsReportService:PressVesShapeSizeInsReportService,public fb: FormBuilder,private msg:NzMessageService){
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
      this.pressVesShapeSizeInsReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+"+压力容器外观及几何尺寸检验报告"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
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
    this.pressVesShapeSizeInsReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
  }
}
