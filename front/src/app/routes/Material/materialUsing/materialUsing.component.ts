import {Component, OnInit} from '@angular/core';
import {MaterialUsingService} from './materialUsing.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-materialUsing',
  templateUrl: 'materialUsing.component.html',
  styleUrls: ['./materialUsing.component.less'],
  providers: [MaterialUsingService]
})
export class MaterialUsingComponent implements OnInit {
  private pageindex = 1;
  private pagesize = 25;
  private dataset = [];
  private total = 0;
  private loading = true;
  validateForm: FormGroup;
  private codedmarking = null;
  constructor(private materialUsingService: MaterialUsingService,private fb: FormBuilder){
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
      this.materialUsingService.getUsingData(this.pageindex,this.pagesize,this.validateForm['controls']["codedmarking"].value).subscribe((res)=>{
        if(res["result"]=="success"){
          this.total = res["total"];
          this.dataset = res["data"];
          this.loading = false;
          this.codedmarking = this.validateForm['controls']["codedmarking"].value;
        }
      });
    }
  }

  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]]
    });
  }
  download(){
    if(this.codedmarking!=null)
      this.materialUsingService.download(this.codedmarking);
  }
}
