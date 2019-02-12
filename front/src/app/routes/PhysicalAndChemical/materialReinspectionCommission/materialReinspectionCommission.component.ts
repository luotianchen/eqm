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
  tensileDetails = ['Rp0.2','Rp1.0','Rm','A'];
  ngOnInit(): void {
    this.materialReinspectionCommissionService.getcodedmarking().then((res:any)=>{
      if(res['result']=="success")
        this.codedmarkings = res.data;
    });
    this.validateForm = this.fb.group({
      "codedmarking":[null, [Validators.required]],
      "explain":["按GB150-2011要求进行材料复验", [Validators.required]],
      "tensile":[true, [Validators.required]],
      "tensileTest":[1],
      "tensileDetail":[[
        { label: 'Rp0.2', value: 'Rp0.2' },
        { label: 'Rp1.0', value: 'Rp1.0' },
        { label: 'Rm', value: 'Rm', checked: true },
        { label: 'A', value: 'A', checked: true }
      ], [Validators.required]],
      "impact":[false, [Validators.required]],
      "impactTest":[""],
      "impactDetail":[""],
      "bending":['无',[Validators.required]],
      "bendingTest":[""],
      "bendingDetail":[""],
      "flattening":['无', [Validators.required]],
      "flatteningTest":[""],
      "other":[false,[Validators.required]],
      "otherDetail":[""],
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
        "forceperformance":this.result(),
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
  result(){
    let resultStr = "";
    if(this.validateForm.value.tensile) {
      resultStr += "拉伸试验" + this.validateForm.value.tensileTest + "个(";
      let flag = false;
      if (this.validateForm.value.tensileDetail[0].checked){
        resultStr += this.validateForm.value.tensileDetail[0].value;
        flag = true;
      }
      if (this.validateForm.value.tensileDetail[1].checked){
        if(flag)
          resultStr +="、";
        resultStr += this.validateForm.value.tensileDetail[1].value;
        flag = true;
      }
      if (this.validateForm.value.tensileDetail[2].checked){
        if(flag)
          resultStr +="、";
        resultStr += this.validateForm.value.tensileDetail[2].value;
        flag = true;
      }
      if (this.validateForm.value.tensileDetail[3].checked){
        if(flag)
          resultStr +="、";
        resultStr += this.validateForm.value.tensileDetail[3].value;
      }
      resultStr += ")。";
    }
    if(this.validateForm.value.impact){
      resultStr += "冲击试验" + this.validateForm.value.impactTest + "个，冲击温度" + this.validateForm.value.impactDetail + "℃。";
    }
    if(this.validateForm.value.bending != '无'){
      resultStr += this.validateForm.value.bending + '试验' + this.validateForm.value.bendingTest + "个，弯曲角度" + this.validateForm.value.bendingDetail + "°。";
    }
    if(this.validateForm.value.flattening != '无'){
      resultStr += this.validateForm.value.flattening + '试验' + this.validateForm.value.flatteningTest + "个。";
    }
    if(this.validateForm.value.other){
      resultStr += "其他："+this.validateForm.value.otherDetail+"";
    }
    return resultStr;
  }
}
