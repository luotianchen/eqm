import {Component, OnInit} from '@angular/core';
import {PressVesProdQuaCerReportService} from './pressVesProdQuaCerReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-pressVesProdQuaCerReport',
  templateUrl: 'pressVesProdQuaCerReport.component.html',
  styleUrls: ['./pressVesProdQuaCerReport.component.less'],
  providers: [PressVesProdQuaCerReportService]
})
export class PressVesProdQuaCerReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public pressVesProdQuaCerReportService:PressVesProdQuaCerReportService,public fb: FormBuilder){
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
    this.pressVesProdQuaCerReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/pressVesProdQuaCerReport.css'];
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
