import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {ChemicalAnalysisTestReportService} from "./chemicalAnalysisTestReport.service";

@Component({
  selector: 'app-chemicalAnalysisTestReport',
  templateUrl: 'chemicalAnalysisTestReport.component.html',
  styleUrls: ['./chemicalAnalysisTestReport.component.less'],
  providers: [ChemicalAnalysisTestReportService]
})
export class ChemicalAnalysisTestReportComponent implements OnInit {
  public codedmarkings = [];
  validateForm: FormGroup;
  ngOnInit(): void {
    this.chemicalAnalysisTestReportService.getCodedmarking().subscribe(res=>{
      if(res['result'] == "success"){
        for(let item of res['data']){
          this.codedmarkings.push(item['codedmarking']);
        }
      }
    })
    this.chemicalAnalysisTestReportService.getDepartments().subscribe(res=>{
      if(res['result'] == "success"){
        this.departments = res['data'];
      }
    })
    this.validateForm = this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]],
      "department":[null, [Validators.required]]
    });
  }
  constructor(public chemicalAnalysisTestReportService: ChemicalAnalysisTestReportService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService) {
  }
  public codedmarking = null;
  public loading = false;
  status = false;
  public departments = [];
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
      formData.append('department', this.validateForm.value.department);
      this.chemicalAnalysisTestReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.codedmarking+"+化学分析报告报表"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
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
