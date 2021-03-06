import {Component, OnInit} from '@angular/core';
import {PressurePartsService} from './pressureParts.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-pressureParts',
  templateUrl: 'pressureParts.component.html',
  styleUrls: ['./pressureParts.component.less'],
  providers: [PressurePartsService]
})
export class PressurePartsComponent implements OnInit {
  validateForm: FormGroup;
  public pageindex = 1;
  public pagesize = 10;
  public total = 0;
  public dataset = [];
  public loading = true;
  public prodno = null;
  constructor(public fb: FormBuilder, public pressurePartsService: PressurePartsService) {
  }

  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      prodno:[null,[Validators.required]],
    });
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
      this.pressurePartsService.getpressureparts(this.pageindex,this.pagesize,this.validateForm.controls['prodno'].value).subscribe((res)=>{
        if(res["result"]=="success"){
          this.total = res["total"];
          this.dataset = res["data2"];
          for(let i=0;i< this.dataset.length;i++){
            this.dataset[i].contrast = res['data1'][i];
            this.dataset[i].index = i+1;
          }
          this.loading = false;
          this.prodno = this.validateForm.controls['prodno'];
        }
      })
    }
  }
}
