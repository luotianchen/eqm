import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PressVesShapeSizeInsReportService} from "./pressVesShapeSizeInsReport.service";

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
  constructor(public pressVesShapeSizeInsReportService:PressVesShapeSizeInsReportService,public fb: FormBuilder){
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
    this.pressVesShapeSizeInsReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/pressVesShapeSizeInsReport.css'];
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
