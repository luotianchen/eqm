import {Component, OnInit} from '@angular/core';
import {QuaPressVesPlanReportService} from './quaPressVesPlanReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){

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
    this.printCSS = ['assets/css/quaPressVesPlanReport.css'];
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
