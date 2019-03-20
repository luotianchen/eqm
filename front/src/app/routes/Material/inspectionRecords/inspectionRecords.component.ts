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
  public prodnos:any;
  public prodno:any;
  public prodname:any;
  public dwgno:any;
  public status = false;
  public dataSet = [];
  codedmarkings = []
  constructor(public fb: FormBuilder, public inspectionRecordsService: InspectionRecordsService) {
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

  getCodedmarkings(){
    if(this.validateForm.value.prodno){
      this.inspectionRecordsService.getdistribute(this.validateForm.value.prodno).subscribe((res) => {
        if (res['result'] == "success") {
          let temp = [];
          for(let item of res['data']){
            if(temp.indexOf(item.codedmarking)==-1)
              temp.push(item.codedmarking)
          }
          this.codedmarkings = temp;
        }
      })
    }
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
          this.status = true;
        }
      })
    }
  }
  printCSS: string[];
  printStyle: string = ".report td{text-align: center;}.text-left{text-align: left!important;}";
  printBtnBoolean = true;
  printComplete() {
    this.printBtnBoolean = true;
  }
  beforePrint() {
    this.printBtnBoolean = false;
  }
}
