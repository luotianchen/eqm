import {Component, OnInit} from '@angular/core';
import {ConQuaInfoFbSheetService} from './ConQuaInfoFbSheet.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-conQuaInfoFbSheet',
  templateUrl: 'ConQuaInfoFbSheet.component.html',
  styleUrls: ['./ConQuaInfoFbSheet.component.less'],
  providers: [ConQuaInfoFbSheetService]
})
export class ConQuaInfoFbSheetComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  constructor(public conQuaInfoFbSheetService:ConQuaInfoFbSheetService,public fb: FormBuilder){
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
    this.conQuaInfoFbSheetService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/conQuaInfoFbSheet.css'];
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
