import {Component, OnInit} from '@angular/core';
import {SingleWarrantyAbsentService} from './singleWarrantyAbsent.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-singleWarrantyAbsent',
  templateUrl: 'singleWarrantyAbsent.component.html',
  styleUrls: ['./singleWarrantyAbsent.component.less'],
  providers: [SingleWarrantyAbsentService]
})
export class SingleWarrantyAbsentComponent implements OnInit {
  private pageindex = 1;
  private pagesize = 25;
  private dataset = [];
  private total = 0;
  private loading = true;
  private matlcode = null;
  validateForm: FormGroup;
  constructor(private singleWarrantyAbsentService: SingleWarrantyAbsentService,private fb: FormBuilder){
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
      this.singleWarrantyAbsentService.getAbsentData(this.pageindex, this.pagesize, this.validateForm['controls']["matlcode"].value).subscribe((res) => {
        if (res["result"] == "success") {
          this.total = res["total"];
          this.dataset = res["data"];
          this.loading = false;
          this.matlcode = this.validateForm['controls']["matlcode"].value;
        }
      });
    }
  }

  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "matlcode":[null, [Validators.required]]
    });
  }
  download(){
    if(this.matlcode!=null)
      this.singleWarrantyAbsentService.download(this.matlcode);
  }
}
