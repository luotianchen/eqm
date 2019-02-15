import {Component, OnInit, TemplateRef} from '@angular/core';
import {VacuumParameterService} from "./vacuumParameter.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-vacuumParameter',
  templateUrl: 'vacuumParameter.component.html',
  styleUrls: ['./vacuumParameter.component.less'],
  providers: [VacuumParameterService]
})
export class VacuumParameterComponent implements OnInit {
  public prodnos:any;
  validateForm: FormGroup;
  dataSet = [];

  ngOnInit(): void {
    this.vacuumParameterService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "type":["格数", [Validators.required]],
      "initnum":[null, [Validators.required]],
      "statnum":[null, [Validators.required]],
      "initpa":[null],
      "statpa":[null],
      "htcurrent":[null, [Validators.required]],
      "initdate":[null, [Validators.required]],
      "enddate":[null, [Validators.required]],
      "sealvacu":[null, [Validators.required]],
      "sealdate":[null, [Validators.required]],
      "testtemp":[null, [Validators.required]],
      "sealtemp":[null, [Validators.required]],
      "vacuop":[null, [Validators.required]],
      "leakoutrate":[null, [Validators.required]],
    });
  }

  searchData(): void {
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.vacuumParameterService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
        }
      })
    }
  }
  choose(){
    if(this.validateForm.value.type=="格数"){
      this.validateForm.controls['initnum'].setValidators(Validators.required);
      this.validateForm.controls['statnum'].setValidators(Validators.required);
      this.validateForm.controls['initpa'].setValidators(null);
      this.validateForm.controls['statpa'].setValidators(null);
    }else{
      this.validateForm.controls['initnum'].setValidators(null);
      this.validateForm.controls['statnum'].setValidators(null);
      this.validateForm.controls['initpa'].setValidators(Validators.required);
      this.validateForm.controls['statpa'].setValidators(Validators.required);
    }
  }
  constructor(public vacuumParameterService: VacuumParameterService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  formatInDate(control){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
  check(){
    if(this.validateForm.value.initdate!=null){
      if(new Date(this.validateForm.value.initdate) > new Date(this.validateForm.value.enddate)){
        this.validateForm.controls['enddate'].setValue(null);
      }
    }
  }
  submitForm(){
    // TODO 这里的公式记得要写上
    console.log((new Date(this.validateForm.value.initdate).getTime() - new Date(this.validateForm.value.enddate).getTime())/1000);
    let q = null;
    if(this.validateForm.value.type == "压力"){
      q = (this.validateForm.value.initpa - this.validateForm.value.statpa) / (this.validateForm.value.initdate - this.validateForm.value.enddate);
    }
    this.validateForm.controls['leakoutrate'].setValue("123");
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.vacuumParameterService.putVacuumParameter({
        "prodno":this.validateForm.value.prodno,
        "prodname":this.validateForm.value.prodno,
        "dwgno":this.validateForm.value.dwgno,
        "initnum":this.validateForm.value.initnum,
        "statnum":this.validateForm.value.statnum,
        "initpa":this.validateForm.value.initpa,
        "statpa":this.validateForm.value.statpa,
        "htcurrent":this.validateForm.value.htcurrent,
        "initdate":this.validateForm.value.initdate,
        "enddate":this.validateForm.value.enddate,
        "sealvacu":this.validateForm.value.sealvacu,
        "sealdate":this.validateForm.value.sealdate,
        "testtemp":this.validateForm.value.testtemp,
        "sealtemp":this.validateForm.value.sealtemp,
        "vacuop":this.validateForm.value.vacuop,
        "leakoutrate":this.validateForm.value.leakoutrate,
        "user":this._storage.get("username")
      }).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '提交成功',
            nzContent: '真空参数提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }
}
