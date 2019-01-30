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
  public pageindex = 1;
  public pagesize = 25;
  public dataset = [];
  public total = 0;
  public loading = true;
  validateForm: FormGroup;
  public codedmarking = null;
  constructor(public materialUsingService: MaterialUsingService,public fb: FormBuilder){
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
    if(this.codedmarking!=null) {this.materialUsingService.download(this.codedmarking).subscribe((res:any)=>{
      let blob = new Blob([res])
      let objectUrl = URL.createObjectURL(blob);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display:none');
      a.setAttribute('href', objectUrl);
      a.setAttribute('download', "content.xls");
      a.click();
      URL.revokeObjectURL(objectUrl);
    });}
  }
}
