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
  public pageindex = 1;
  public pagesize = 25;
  public dataset = [];
  public total = 0;
  public loading = true;
  public matlcode = null;
  public inyear = null;
  public inmonth = null;
  validateForm: FormGroup;
  public status = false;
  public thisyear = new Date().getFullYear();
  constructor(public monthMaterialService: MonthMaterialService,public fb: FormBuilder){
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
      this.monthMaterialService.download(this.matlcode,this.inyear,this.inmonth).subscribe((res:any)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', "月材料查询.xls");
        a.click();
        URL.revokeObjectURL(objectUrl);
      });
  }
}
