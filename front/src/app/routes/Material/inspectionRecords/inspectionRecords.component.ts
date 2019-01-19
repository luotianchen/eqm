import {Component, OnInit} from '@angular/core';
import {InspectionRecordsService} from './inspectionRecords.service';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-inspectionRecords',
  templateUrl: 'inspectionRecords.component.html',
  styleUrls: ['./inspectionRecords.component.less'],
  providers: [InspectionRecordsService]
})
export class InspectionRecordsComponent implements OnInit {
  validateForm: FormGroup;
  private prodnos:any;
  private prodno:any;
  private prodname:any;
  private dwgno:any;
  private status = false;
  private dataSet = [];
  constructor(private fb: FormBuilder, private inspectionRecordsService: InspectionRecordsService) {
  }
  ngOnInit(): void {
    this.inspectionRecordsService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "codedmarking":[null]
    });
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid) {
      this.inspectionRecordsService.getdistribute(this.validateForm.value.prodno).subscribe((res) => {
        if (res['result'] == "success") {
          this.prodno = this.validateForm.value.prodno;
          this.prodname = res['prodname'];
          this.dwgno = res['dwgno'];
          let dataSet = res['data'];
          this.dataSet = [];
          for(let data of dataSet){
            if(data.codedmarking == this.validateForm.value.codedmarking){
              this.dataSet.push(data);
            }
          }
          console.log(this.dataSet);
          this.status = true;
        }
      })
    }
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
