import {Component, OnInit} from '@angular/core';
import {ReportService} from './Report.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-report',
  templateUrl: 'Report.component.html',
  styleUrls: ['./Report.component.less'],
  providers: [ReportService]
})
export class ReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public reportService:ReportService,public fb: FormBuilder){
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
    this.reportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/pressTestReport.css'];
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
