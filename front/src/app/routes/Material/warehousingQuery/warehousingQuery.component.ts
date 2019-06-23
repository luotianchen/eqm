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
  public pageindex = 1;
  public pagesize = 25;
  public total = 0;
  public loading = true;
  public matlnameDataSet = [];
  public matlnames = [];
  public millunitDataSet = [];
  public millunits = [];
  public specs = [];
  public heatbatchnos = [];
  public codedmarkings = [];
  public dataset = [];
  public designationDataSet = [];
  public designations = [];
  username2name = {};
  users = [];
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
  onCodedmarkingInput(value: string): void {
    this.warehousingQueryService.getCodedmarking(value).subscribe((res) => {
      if (res["result"] == "success") {
        this.codedmarkings = res['data'];
      }
    });
  }
  onMillunitInput(value: string): void {
    this.millunits = value? this.millunitDataSet.filter(item=>item.indexOf(value)!=-1) :this.millunitDataSet;
  }
  onDesignationInput(value: string): void {
    this.designations = value?this.designationDataSet.filter(item=>item.indexOf(value)!=-1):this.designationDataSet;
  }
  onMatlnameInput(value: string): void {
    this.matlnames = value?this.matlnameDataSet.filter(item=>item.indexOf(value)!=-1):this.matlnameDataSet;
  }
  onHeatbatchnoInput(value: string): void{
    this.warehousingQueryService.searchHeatbatchno(value).subscribe((res) => {
      if (res["result"] == "success") {
        this.heatbatchnos = res['data'];
      }
    });
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
  constructor(public fb: FormBuilder, public warehousingQueryService: WarehousingQueryService) {
    this.warehousingQueryService.getputmaterial().subscribe(res => {
      this.matlnameDataSet = res['data']['matlname'];
      this.millunitDataSet = res['data']['millunit'];
      this.designationDataSet = res['data']['designation'];
    });
    this.warehousingQueryService.searchHeatbatchno(null).subscribe(res=>{
      this.heatbatchnos = res['data'];
    })
    this.warehousingQueryService.getCodedmarking(null).subscribe(res=>{
      if(res['result'] == "success"){
        this.codedmarkings = res['data'];
      }
    })
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }

  public utclass = {
    "0":"/",
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
      "status":[null],
      "heatbatchno":[null]
    });
    this.searchData();
  }
  formatInDate(){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
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
    this.warehousingQueryService.searchallmaterial(this.pageindex,this.pagesize,this.validateForm.value.codedmarking,this.validateForm.value.matlname,this.validateForm.value.designation,this.validateForm.value.spec,this.validateForm.value.millunit,this.validateForm.value.heatbatchno,this.validateForm.value.indate,this.validateForm.value.status).subscribe((res)=>{
      if(res["result"]=="success"){
        this.total = res["total"];
        this.dataset = res["data"];
        this.loading = false;
      }else{
        this.total = 0;
        this.dataset = [];
        this.loading = false;
      }
    })
  }
}
