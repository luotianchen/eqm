import {Component, OnInit} from '@angular/core';
import {VacuumInspectionReportService} from './vacuumInspectionReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-vacuumInspectionReport',
  templateUrl: 'vacuumInspectionReport.component.html',
  styleUrls: ['./vacuumInspectionReport.component.less'],
  providers: [VacuumInspectionReportService]
})
export class VacuumInspectionReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public vacuumInspectionReportService:VacuumInspectionReportService,public fb: FormBuilder){
  }
  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){

    }
  }
  ngOnInit(): void {
    this.vacuumInspectionReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/vacuumInspectionReport.css'];
  }
  printCSS: string[];
  printStyle: string;
  printBtnBoolean = true;
  printComplete() {
    this.printBtnBoolean = true;
  }
  beforePrint() {
    this.printBtnBoolean = false;
  }
}
