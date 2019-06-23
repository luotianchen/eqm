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
  public codedmarkingDataSet = [];
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
    this.codedmarkings = value? this.codedmarkingDataSet.filter(item=>item.codedmarking.indexOf(value)!=-1) :this.codedmarkingDataSet;
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
    this.warehousingAuditService.searchHeatbatchno(value).subscribe((res) => {
      if (res["result"] == "success") {
        this.heatbatchnos = res['data'];
      }
    });
  }
  constructor(public fb: FormBuilder, public warehousingAuditService:WarehousingAuditService,public message : NzMessageService,public _storage:SessionStorageService) {
    this.warehousingAuditService.getputmaterial().subscribe(res => {
      if (res['result'] === 'success') {
        this.matlnameDataSet = res['data']['matlname'];
        this.millunitDataSet = res['data']['millunit'];
        this.designationDataSet = res['data']['designation'];
      }
    });
    this.warehousingAuditService.searchHeatbatchno(null).subscribe(res=>{
      this.heatbatchnos = res['data'];
    });
    this.warehousingAuditService.getCodedmarking().subscribe(res=>{
      if(res['result'] == "success"){
        this.codedmarkingDataSet = res['data'];
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
      "heatbatchno":[null]
    });
    this.warehousingAuditService.getuserform().subscribe(res=>{
      if(res['result']=='success'){
        this.users = res['data'];
        for(let user of this.users){
          this.username2name[user.username] = user.name;
        }
        this.searchData();
      }
    });
  }
  formatInDate(){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
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
    this.warehousingAuditService.searchallmaterial(this.pageindex,this.pagesize,this.validateForm.controls['codedmarking'].value,this.validateForm.value.matlname,this.validateForm.value.designation,this.validateForm.value.spec,this.validateForm.value.millunit,this.validateForm.value.heatbatchno,this.validateForm.value.indate).subscribe((res)=>{
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
  Audit(codedmarking,status,user){
    this.warehousingAuditService.audit(codedmarking,status,this._storage.get("username")).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        if(status == 2){
            this.warehousingAuditService.putmessage(user,codedmarking,this._storage.get("name")).subscribe(res=>{
              if(res['result'] == "success"){
                // 发送成功
              }
            })
        }
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }
}
