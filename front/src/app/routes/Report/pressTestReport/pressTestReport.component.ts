import {Component, OnInit} from '@angular/core';
import {pressTestReportService} from './pressTestReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-pressTestReport',
  templateUrl: 'pressTestReport.component.html',
  styleUrls: ['./pressTestReport.component.less'],
  providers: [pressTestReportService]
})
export class PressTestReportComponent implements OnInit {
  validateForm: FormGroup;
  public prodno = null;
  public prodnos = [];
  public dwgno = null;
  public prodname = null;
  public status = false;
  public dataDetail = [];
  public dates = {
    year:null,
    month:null,
    day:null,
    week:null
  };
  usersign :string = null;
  audit_usersign:string = null;
  num2week = ["一","二","三","四","五","六","日"];
  constructor(public pressTestReportService:pressTestReportService,public fb: FormBuilder){
  }
  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
        this.pressTestReportService.searchpresstest(this.validateForm.value.prodno).subscribe(res=>{
          if(res['result'] == 'success'){
            this.prodno = this.validateForm.value.prodno;
            this.status = true;
            this.dataDetail = res['data'];
            this.dates.year = this.dataDetail['date'].split("-")[0];
            this.dates.month = this.dataDetail['date'].split("-")[1];
            this.dates.day = this.dataDetail['date'].split("-")[2];
            this.dates.week = this.num2week[new Date(this.dataDetail['date']).getDay()];
            this.pressTestReportService.getSignImage(this.dataDetail['user']).then(res=>{
              if(res['result'] == 'success'){
                this.usersign = res['url'];
              }else{
                this.usersign = null;
              }
            })
            this.pressTestReportService.getSignImage(this.dataDetail['audit_user']).then(res=>{
              if(res['result'] == 'success'){
                this.audit_usersign = res['url'];
              }else{
                this.audit_usersign = null;
              }
            })
          }else{
            this.status = false;
          }
        })
    }
  }
  ngOnInit(): void {
    this.pressTestReportService.getprodno().subscribe((res) => {
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
