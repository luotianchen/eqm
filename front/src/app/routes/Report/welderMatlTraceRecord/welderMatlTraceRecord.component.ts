import {Component, OnInit} from '@angular/core';
import {WelderMatlTraceRecordService} from './welderMatlTraceRecord.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-welderMatlTraceRecord',
  templateUrl: 'welderMatlTraceRecord.component.html',
  styleUrls: ['./welderMatlTraceRecord.component.less'],
  providers: [WelderMatlTraceRecordService]
})
export class WelderMatlTraceRecordComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public welderMatlTraceRecordService:WelderMatlTraceRecordService,public fb: FormBuilder){
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
    this.welderMatlTraceRecordService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/welderMatlTraceRecord.css'];
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
