import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MonthMaterialService} from "./monthMaterial.service";

@Component({
  selector: 'app-monthMaterial',
  templateUrl: 'monthMaterial.component.html',
  styleUrls: ['./monthMaterial.component.less'],
  providers: [MonthMaterialService]
})
export class MonthMaterialComponent implements OnInit {
  private pageindex = 1;
  private pagesize = 25;
  private dataset = [];
  private total = 0;
  private loading = true;
  private matlcode = null;
  private inyear = null;
  private inmonth = null;
  validateForm: FormGroup;
  private status = false;
  private thisyear = new Date().getFullYear();
  constructor(private monthMaterialService: MonthMaterialService,private fb: FormBuilder){
  }
  searchData(reset: boolean = false): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      if (reset) {
        this.pageindex = 1;
      }
      this.loading = true;
      this.monthMaterialService.getMonthData(this.pageindex,this.pagesize,this.validateForm['controls']["matlcode"].value,this.validateForm['controls']["inyear"].value,this.validateForm['controls']["inmonth"].value).subscribe((res)=>{
        if(res["result"]=="success"){
          this.total = res["total"];
          this.dataset = res["data"];
          this.loading = false;
          this.status = true;
          this.matlcode = this.validateForm['controls']["matlcode"].value;
          this.inyear = this.validateForm['controls']["inyear"].value;
          this.inmonth = this.validateForm['controls']["inmonth"].value;
        }else{
          this.status = false;
        }
      });
    }
  }

  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "matlcode":[null, [Validators.required]],
      "inyear":[null, [Validators.required]],
      "inmonth":[null, [Validators.required]],
    });
  }

  download(){
    if(this.status)
      this.monthMaterialService.download(this.matlcode,this.inyear,this.inmonth);
  }
}
