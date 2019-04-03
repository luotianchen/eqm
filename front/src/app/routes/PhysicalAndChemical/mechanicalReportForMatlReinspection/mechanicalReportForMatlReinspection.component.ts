import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {MechanicalReportForMatlReinspectionService} from "./mechanicalReportForMatlReinspection.service";

@Component({
  selector: 'app-mechanicalReportForMatlReinspection',
  templateUrl: 'mechanicalReportForMatlReinspection.component.html',
  styleUrls: ['./mechanicalReportForMatlReinspection.component.less'],
  providers: [MechanicalReportForMatlReinspectionService]
})
export class MechanicalReportForMatlReinspectionComponent implements OnInit {
  constructor(public mechanicalReportForMatlReinspectionService: MechanicalReportForMatlReinspectionService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService) {
  }

  public codedmarkings = [];
  public specimennos = [];
  public departments = [];
  validateForm: FormGroup;
  ngOnInit(): void {
    this.mechanicalReportForMatlReinspectionService.getCodedmarking().subscribe(res=>{
      if(res['result'] == "success")
        this.codedmarkings = res['data'];
    })
    this.mechanicalReportForMatlReinspectionService.getDepartments().subscribe(res=>{
      if(res['result'] == "success"){
        this.departments = res['data'];
      }
    })
    this.validateForm = this.validateForm = this.fb.group({
      "department":[null, [Validators.required]],
      "area":[null, [Validators.required]],
      "codedmarking":[null, [Validators.required]]
    });
  }
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
      formData.append('area', this.validateForm.value.area);
      formData.append('department', this.validateForm.value.department);
      this.mechanicalReportForMatlReinspectionService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.codedmarking+"+力学性能试验报告"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
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
