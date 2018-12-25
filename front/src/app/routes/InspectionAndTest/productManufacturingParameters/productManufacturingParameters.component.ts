import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProductManufacturingParametersService} from "./productManufacturingParameters.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productManufacturingParameters',
  templateUrl: 'productManufacturingParameters.component.html',
  styleUrls: ['./productManufacturingParameters.component.less'],
  providers: [ProductManufacturingParametersService]
})
export class ProductManufacturingParametersComponent implements OnInit {
  private prodnos:any;
  validateForm: FormGroup;
  dataSet = [];

  ngOnInit(): void {
    this.productManufacturingParametersService.getprodno().subscribe((res) => {
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
      this.productManufacturingParametersService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
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
  constructor(private productManufacturingParametersService: ProductManufacturingParametersService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }

  formatInDate(control){
    let monthDay = /^([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
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
    this.validateForm.controls['leakoutrate'].setValue("123")
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      console.log(JSON.stringify(this.validateForm.value))
    }
  }
}
