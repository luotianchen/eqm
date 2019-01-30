import {Component, OnInit, TemplateRef} from '@angular/core';
import {MaterialReinspectionCommissionService} from "./materialReinspectionCommission.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialReinspectionCommission',
  templateUrl: 'materialReinspectionCommission.component.html',
  styleUrls: ['./materialReinspectionCommission.component.less'],
  providers: [MaterialReinspectionCommissionService]
})
export class  MaterialReinspectionCommissionComponent implements OnInit {
  public codedmarkings:any;
  validateForm: FormGroup;
  ngOnInit(): void {
    this.materialReinspectionCommissionService.getcodedmarking().then((res:any)=>{
      if(res['result']=="success")
        this.codedmarkings = res.data;
    });
    this.validateForm = this.fb.group({
        "codedmarking":[null, [Validators.required]],
        "explain":["按GB150-2011要求进行材料复验", [Validators.required]],
        "forceperformance":["拉伸1个（Rm、A）", [Validators.required]],
        "chemicalcomposition":["无",[Validators.required]]
    });
  }

  constructor(public materialReinspectionCommissionService: MaterialReinspectionCommissionService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.materialReinspectionCommissionService.putproducttestboardcommission({
        "codedmarking":this.validateForm.value.codedmarking,
        "explain":this.validateForm.value.explain,
        "forceperformance":this.validateForm.value.forceperformance,
        "chemicalcomposition":this.validateForm.value.chemicalcomposition,
        "user":this._storage.get("username")
      }).then((res:any)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }
}
