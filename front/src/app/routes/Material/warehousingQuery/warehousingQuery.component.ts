import {Component, OnInit} from '@angular/core';
import {WarehousingQueryService} from './warehousingQuery.service';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-warehousingQuery',
  templateUrl: 'warehousingQuery.component.html',
  styleUrls: ['./warehousingQuery.component.less'],
  providers: [WarehousingQueryService]
})
export class WarehousingQueryComponent implements OnInit {
  validateForm: FormGroup;
  private pageindex = 1;
  private pagesize = 25;
  private total = 0;
  private loading = true;
  private matlname = [];
  private millunits = [];
  private specs = [];
  private dataset = [];
    onSpecInput(value: string): void {
    this.specs = value ? [
      value,
      "φ"+value,
      "δ="+value,
      "EHA"+value,
      "∠"+value,
      "DHB"+value,
      "T"+value,
      "12~40目",
      "10~60目"
    ]:[
      "φ"+value,
      "δ="+value,
      "EHA"+value,
      "∠"+value,
      "DHB"+value,
      "T"+value,
      "12~40目",
      "10~60目"
    ];
  }
  statusQ = [{
    value:-1,
    label:"全部"
  },{
      value:1,
      label:"已审核"
  },{
    value:0,
    label:"未审核"
  }];
  constructor(private fb: FormBuilder, private warehousingQueryService: WarehousingQueryService) {
    this.warehousingQueryService.getputmaterial().subscribe(res => {
      if (res['result'] === 'success') {
        this.matlname = res['data']['matlname'];
        this.millunits = res['data']['millunit'];
      }
    });
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }

  private utclass = {
    "1":"I",
    "2":"II",
    "3":"III",
    "4":"IV"
  };
  ngOnInit(): void {
    this.validateForm = this.validateForm = this.fb.group({
      "codedmarking":[null],
      "matlname":[null],
      "designation":[null],
      "spec":[null],
      "millunit":[null],
      "indate":[null],
      "status":[null]
    });
    this.searchData();
  }
  formatInDate(){
    let monthDay = /^([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.value.indate)){
      this.validateForm.controls["indate"].setValue(new Date().getFullYear()+"-"+this.validateForm.value.indate);
    }else if(!yearMonthDay.test(this.validateForm.value.indate)){
      this.validateForm.controls["indate"].setValue(null);
    }
  }

  searchData(reset: boolean = false): void {
    if (reset) {
      this.pageindex = 1;
    }
    this.loading = true;
    if(this.validateForm.value.status==null){
      this.validateForm.controls['status'].setValue(1);
    }
    this.warehousingQueryService.searchallmaterial(this.pageindex,this.pagesize,this.validateForm.value.codedmarking,this.validateForm.value.matlname,this.validateForm.value.designation,this.validateForm.value.spec,this.validateForm.value.millunit,this.validateForm.value.indate,this.validateForm.value.status).subscribe((res)=>{
      if(res["result"]=="success"){
        this.total = res["total"];
        this.dataset = res["data"];
        this.loading = false;
      }
    })
  }
}