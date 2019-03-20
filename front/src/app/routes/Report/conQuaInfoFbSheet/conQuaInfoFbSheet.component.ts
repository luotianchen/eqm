import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConQuaInfoFbSheetService} from "./conQuaInfoFbSheet.service";
import {NzMessageService} from "ng-zorro-antd";

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
  public loading = false;
    status = false;
    constructor(public conQuaInfoFbSheetService:ConQuaInfoFbSheetService,public fb: FormBuilder,private msg:NzMessageService){
    }
    submitForm(): void {
      // tslint:disable-next-line:no-any
      for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.loading = true;
      const formData = new FormData();
      formData.append('prodno', this.validateForm.value.prodno);
      this.conQuaInfoFbSheetService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+"+用户质量信息反馈单"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
        a.click();
        URL.revokeObjectURL(objectUrl);
        this.loading = false;
      },err=>{
        this.loading = false;
        this.msg.error("出现异常，请稍后重试！")
      })
    }
  }
    ngOnInit(): void {
      this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
    this.conQuaInfoFbSheetService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
  }
}
