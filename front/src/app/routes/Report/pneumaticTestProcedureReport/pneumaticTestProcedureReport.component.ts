import {Component, OnInit} from '@angular/core';
import {PneumaticTestProcedureReportService} from './pneumaticTestProcedureReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-pneumaticTestProcedureReport',
  templateUrl: 'pneumaticTestProcedureReport.component.html',
  styleUrls: ['./pneumaticTestProcedureReport.component.less'],
  providers: [PneumaticTestProcedureReportService]
})
export class PneumaticTestProcedureReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public pneumaticTestProcedureReportService:PneumaticTestProcedureReportService,public fb: FormBuilder){
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
    this.pneumaticTestProcedureReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/pneumaticTestProcedureReport.css'];
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
