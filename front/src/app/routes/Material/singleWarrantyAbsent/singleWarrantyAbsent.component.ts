import {Component, OnInit} from '@angular/core';
import {SingleWarrantyAbsentService} from './singleWarrantyAbsent.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-singleWarrantyAbsent',
  templateUrl: 'singleWarrantyAbsent.component.html',
  styleUrls: ['./singleWarrantyAbsent.component.less'],
  providers: [SingleWarrantyAbsentService]
})
export class SingleWarrantyAbsentComponent implements OnInit {
  public pageindex = 1;
  public pagesize = 25;
  public dataset = [];
  public total = 0;
  public loading = true;
  public matlcode = null;
  public index = null;
  validateForm: FormGroup;
  designations = [];
  constructor(public singleWarrantyAbsentService: SingleWarrantyAbsentService,public fb: FormBuilder){
  }

  searchData(reset: boolean = false): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid) {
      if (reset) {
        this.pageindex = 1;
      }
      this.loading = true;
      this.singleWarrantyAbsentService.getAbsentData(this.pageindex, this.pagesize, this.validateForm['controls']["matlcode"].value,this.validateForm.value.designation).subscribe((res) => {
        if (res["result"] == "success") {
          this.total = res["total"];
          this.dataset = res["data"];
          this.loading = false;
          this.matlcode = this.validateForm['controls']["matlcode"].value;
        }else{
          this.total = 0;
          this.dataset = [];
          this.loading = false;
        }
      });
    }
  }

  ngOnInit(): void {
    this.singleWarrantyAbsentService.getputmaterial().subscribe(res=>{
      if(res['result'] == "success"){
          this.designations = res['data']['designation'];
      }
    })
    this.validateForm = this.validateForm = this.fb.group({
      "matlcode":[null, [Validators.required]],
      "designation":[null]
    });
  }
  download(){
    if(this.matlcode!=null) {this.singleWarrantyAbsentService.download(this.matlcode,this.validateForm.value.designation).subscribe((res:any)=>{
      let blob = new Blob([res])
      let objectUrl = URL.createObjectURL(blob);
      let a = document.createElement('a');
      document.body.appendChild(a);
      let date = new Date();
      a.setAttribute('style', 'display:none');
      a.setAttribute('href', objectUrl);
      a.setAttribute('download', "单项质保书未到"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xls");
      a.click();
      URL.revokeObjectURL(objectUrl);
    });}
  }
}
