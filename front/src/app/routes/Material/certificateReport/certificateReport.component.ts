import {Component, OnInit} from '@angular/core';
import {CertificateReportService} from './certificateReport.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-certificateReport',
  templateUrl: 'certificateReport.component.html',
  styleUrls: ['./certificateReport.component.less'],
  providers: [CertificateReportService]
})
export class CertificateReportComponent implements OnInit {
  validateForm: FormGroup;
  public codedmarking = null;
  public codedmarkings = [];
  public value : any;
  public information = "";
  public now = new Date();
  year = null;
  month = null;
  day = null;
  logourl = null;
  constructor(public certificateReportService:CertificateReportService,public fb: FormBuilder){
  }
  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.codedmarking = null;
      this.certificateReportService.getmaterial(this.validateForm.value.codedmarking).subscribe(res=>{
        if(res['result']=="success"){
          this.value = res['data'];
          this.information = "材质书号："+this.value.codedmarking+"，标准："+this.value.modelstand+"，牌号："+this.value.designation+"，炉批号："+this.value.heatbatchno+"，规格："+this.value.spec+"，制造厂："+this.value.millunit+"，检验员："+this.value.user+"，检查日期："+this.value.indate;
          console.log(this.information);
          this.codedmarking = this.validateForm.value.codedmarking;
        }
      })
    }
  }
  ngOnInit(): void {
    this.year = this.now.getFullYear();
    this.month = this.now.getMonth()+1;
    this.day = this.now.getDate();
    this.certificateReportService.getlogo().subscribe(res=>{
      if(res['result'] == "success"){
        this.logourl = res['url'];
      }
    });
    this.certificateReportService.getcodedmarking().subscribe((res) => {
      if (res["result"] == "success") {
        this.codedmarkings = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]]
    });
    this.printCSS = ['assets/css/certificateReport.css'];
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
