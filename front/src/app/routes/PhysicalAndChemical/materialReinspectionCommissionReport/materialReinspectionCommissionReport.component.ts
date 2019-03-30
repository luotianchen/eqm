import {Component, OnInit} from '@angular/core';
import {MaterialReinspectionCommissionReportService} from "./materialReinspectionCommissionReport.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";

@Component({
  selector: 'app-materialReinspectionCommissionReport',
  templateUrl: 'materialReinspectionCommissionReport.component.html',
  styleUrls: ['./materialReinspectionCommissionReport.component.less'],
  providers: [MaterialReinspectionCommissionReportService]
})
export class  MaterialReinspectionCommissionReportComponent implements OnInit {
  public codedmarkings = [];
  validateForm: FormGroup;
  ngOnInit(): void {
    this.materialReinspectionCommissionReportService.getCodedmarking().subscribe(res=>{
      if(res['result'] == "success"){
        for(let item of res['data']){
          if(this.codedmarkings.indexOf(item['codedmarking'])==-1)
            this.codedmarkings.push(item['codedmarking']);
        }
      }
    })
    this.validateForm = this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]]
    });
  }
  constructor(public materialReinspectionCommissionReportService: MaterialReinspectionCommissionReportService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService) {
  }
  public codedmarking = null;
  public loading = false;
  status = false;
  submitForm(): void {
    // tslint:disable-next-line:no-any
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.loading = true;
      const formData = new FormData();
      formData.append('codedmarking', this.validateForm.value.codedmarking);
      this.materialReinspectionCommissionReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.codedmarking+"+材料复验申请单"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
        a.click();
        URL.revokeObjectURL(objectUrl);
        this.loading = false;
      },err=>{
        this.loading = false;
        this.message.error("出现异常，请稍后重试！")
      })
    }
  }
}
