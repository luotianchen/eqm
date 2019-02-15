import {Component, OnInit} from '@angular/core';
import {QuaPlanPressVesReportService} from './quaPlanPressVesReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-quaPlanPressVesReport',
  templateUrl: 'quaPlanPressVesReport.component.html',
  styleUrls: ['./quaPlanPressVesReport.component.less'],
  providers: [QuaPlanPressVesReportService]
})
export class QuaPlanPressVesReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public quaPlanPressVesReportService:QuaPlanPressVesReportService,public fb: FormBuilder){
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
    this.quaPlanPressVesReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/quaPlanPressVesReport.css'];
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
