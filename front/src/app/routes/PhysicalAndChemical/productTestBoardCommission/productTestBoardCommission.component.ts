import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProductTestBoardCommissionService} from "./productTestBoardCommission.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productTestBoardCommission',
  templateUrl: 'productTestBoardCommission.component.html',
  styleUrls: ['./productTestBoardCommission.component.less'],
  providers: [ProductTestBoardCommissionService]
})
export class ProductTestBoardCommissionComponent implements OnInit {
  public prodnos:any;
  validateForm: FormGroup;
  testboardstandValidateForm: FormGroup;
  dataSet = [];
  userinfo = [];
  designations = [];
  specs = [];
  representparts = [
    "纵缝",
    "环缝"
  ]
  ngOnInit(): void {
    this.productTestBoardCommissionService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.productTestBoardCommissionService.getUsers().subscribe((res)=>{
      if(res['result']=='success'){
        this.userinfo = res['data'];
      }
    })
    this.productTestBoardCommissionService.getDesignationAndSpec().subscribe((res)=>{
      if(res['result']=="success"){
        this.designations = res['data']['designation'];
        this.specs = res['data']['spec'];
      }
    })
      this.validateForm = this.fb.group({
        "prodno":[null, [Validators.required]],
        "prodname":[null, [Validators.required]],
        "evaluastand":["NB/T47016-2011", [Validators.required]],
        "specimenno":[null,[Validators.required]],
        "specimenname":["产品试板",[Validators.required]],
        "weldingsteelseal":[null,[Validators.required]],
        "designation":[null,[Validators.required]],
        "spec":[null,[Validators.required]],
        "groovetype":[null,[Validators.required]],
        "weldingmethod":[null,[Validators.required]],
        "weldingposition":[null,[Validators.required]],
        "heatcondi":[null,[Validators.required]],
        "representno":["/",[Validators.required]],
        "representpart":["纵缝",[Validators.required]],
        "drawingnumber":[1,[Validators.required]],
        "surfacebending":[1,[Validators.required]],
        "backbending":[1,[Validators.required]],
        "lateralbending":[null,[Validators.required]],
        "flattening":["/",[Validators.required]],
        "shocktemperature":[null,[Validators.required]],
        "weldzoneshocknum":["/",[Validators.required]],
        "thermalimpactzonenum":["/",[Validators.required]],
    });
    this.testboardstandValidateForm = this.fb.group({
      "testboardstand":[null, [Validators.required]]
    })
  }

  constructor(private productTestBoardCommissionService: ProductTestBoardCommissionService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }

  searchData(): void {
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.productTestBoardCommissionService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
        }
      })
    }
  }

  changeLateralbending(){
    if (this.validateForm.value.surfacebending==1 || this.validateForm.value.surfacebending==2) {
      this.validateForm.controls['lateralbending'].setValue('/');
    }else if(this.validateForm.value.surfacebending=='/'){
      this.validateForm.controls['lateralbending'].setValue(2);
    }
  }
  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    console.log(this.validateForm.valid);
    console.log(this.validateForm.controls);
    if(this.validateForm.valid){
      this.productTestBoardCommissionService.putproducttestboardcommission(
        {
          "prodno":this.validateForm.value.prodno,
          "evaluastand":this.validateForm.value.evaluastand,
          "specimenno":this.validateForm.value.specimenno,
          "specimenname":this.validateForm.value.specimenname,
          "weldingsteelseal":this.validateForm.value.weldingsteelseal,
          "designation":this.validateForm.value.designation,
          "spec":this.validateForm.value.spec,
          "groovetype":this.validateForm.value.groovetype,
          "weldingmethod":this.validateForm.value.weldingmethod,
          "weldingposition":this.validateForm.value.weldingposition,
          "heatcondi":this.validateForm.value.heatcondi,
          "representno":this.validateForm.value.representno,
          "representpart":this.validateForm.value.representpart,
          "drawingnumber":this.validateForm.value.drawingnumber,
          "surfacebending":this.validateForm.value.surfacebending,
          "backbending":this.validateForm.value.backbending,
          "lateralbending":this.validateForm.value.lateralbending,
          "flattening":this.validateForm.value.flattening,
          "shocktemperature":this.validateForm.value.shocktemperature,
          "weldzoneshocknum":this.validateForm.value.weldzoneshocknum,
          "thermalimpactzonenum":this.validateForm.value.thermalimpactzonenum,
          "user":this._storage.get("username")
        }
      ).subscribe((res)=>{
        if (res['result']=="success"){
          this.modalService.success({
            nzTitle: '提交成功',
            nzContent: '试板委托提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }

}
