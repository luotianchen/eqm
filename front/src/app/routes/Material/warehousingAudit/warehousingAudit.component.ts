import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {WarehousingAuditService} from "./warehousingAudit.service";
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
@Component({
  selector: 'app-warehousingAudit',
  templateUrl: 'warehousingAudit.component.html',
  styleUrls: ['./warehousingAudit.component.less'],
  providers: [WarehousingAuditService]
})

export class WarehousingAuditComponent implements OnInit {
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
  constructor(private fb: FormBuilder, private warehousingAuditService:WarehousingAuditService,private message : NzMessageService,private _storage:SessionStorageService) {
    this.warehousingAuditService.getputmaterial().subscribe(res => {
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
      "indate":[null]
    });
    this.searchData();
  }
  formatInDate(){
    let monthDay = /^([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([1-9]|1[0-2])-([1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.validateForm.controls["indate"].value)){
      this.validateForm.controls["indate"].setValue(new Date().getFullYear()+"-"+this.validateForm.controls["indate"].value);
    }else if(!yearMonthDay.test(this.validateForm.value.indate)){
      this.validateForm.controls["indate"].setValue(null);
    }
  }

  searchData(reset: boolean = false): void {
    if (reset) {
      this.pageindex = 1;
    }
    this.loading = true;
    this.warehousingAuditService.searchallmaterial(this.pageindex,this.pagesize,this.validateForm.controls['codedmarking'].value,this.validateForm.value.matlname,this.validateForm.value.designation,this.validateForm.value.spec,this.validateForm.value.millunit,this.validateForm.value.indate).subscribe((res)=>{
      if(res["result"]=="success"){
        this.total = res["total"];
        this.dataset = res["data"];
        this.loading = false;
      }
    })
  }
  Audit(codedmarking,status){
    this.warehousingAuditService.audit(codedmarking,status,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
}
