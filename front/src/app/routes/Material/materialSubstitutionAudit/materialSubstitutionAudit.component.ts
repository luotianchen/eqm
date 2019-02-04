import {Component, OnInit} from '@angular/core';
import {MaterialSubstitutionAuditService} from "./materialSubstitutionAudit.service";
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialSubstitutionAudit',
  templateUrl: 'materialSubstitutionAudit.component.html',
  styleUrls: ['./materialSubstitutionAudit.component.less'],
  providers: [MaterialSubstitutionAuditService]
})
export class MaterialSubstitutionAuditComponent implements OnInit {
  public dataSet:any;
  public dataDetail = {};
  isVisible = true;
  public roles = [
    this._storage.get("role")
  ];
  public rolename;
  public role_audit = {
    "设计责任工程师" : ["design_status","status_c"],
    "材料责任工程师" : ["matl_status"],
    "焊接责任工程师" : ["welding_status"],
    "工艺责任工程师":["process_status"],
    "检验与试验责任工程师":["inspection_status"],
    "技术负责人":["status_b"],
    "超级管理员":["design_status","matl_status","welding_status","process_status","inspection_status","status_b","status_c"]
  };
  audit_status = {
    design_status:0,
    matl_status:0,
    welding_status:0,
    process_status:0,
    inspection_status:0,
    status_b:0,
    status_c:0
  };
  audit_result = {
    audit:null,
    design_status:0,
    matl_status:0,
    welding_status:0,
    process_status:0,
    inspection_status:0,
    status_b:0,
    status_c:0,
    design_note:null,
    matl_note:null,
    welding_note:null,
    process_note:null,
    inspection_note:null,
    b_note:null,
    c_note:null
  };

  audit_user = {
    design_status:"design_username",
    matl_status:"matl_username",
    welding_status:"welding_username",
    process_status:"process_username",
    inspection_status:"inspection_username",
    status_b:"b_username",
    status_c:"c_username",
  };

  constructor(public materialSubstitutionAuditService:MaterialSubstitutionAuditService,public message : NzMessageService,public _storage:SessionStorageService){
  }

  search(audit){
    this.materialSubstitutionAuditService.getSubstitution(audit).subscribe((response)=>{
      if(response['result'] == "success"){
        this.dataDetail[audit] = response['data'];
      }
    })
  }
  ngOnInit(): void {
    if(this._storage.get("substitutionRole")!=null){
      this.rolename = this._storage.get("substitutionRole");
      this.isVisible = false;
      this.searchData();
    }
    if(this._storage.get("role2")!="null")
      this.roles.push(this._storage.get("role2"));
    if(this._storage.get("role3")!="null")
      this.roles.push(this._storage.get("role3"));
    if(this._storage.get("role4")!="null")
      this.roles.push(this._storage.get("role4"));
    if(this._storage.get("role5")!="null")
      this.roles.push(this._storage.get("role5"))
  }
  searchData(){
    this.audit_status = {
      design_status:0,
      matl_status:0,
      welding_status:0,
      process_status:0,
      inspection_status:0,
      status_b:0,
      status_c:0
    };
    for(let role of this.role_audit[this.rolename]){
      this.audit_status[role] = 1;
    }
    if(this.rolename == "超级管理员")
      this.audit_status = {
        design_status:1,
        matl_status:1,
        welding_status:1,
        process_status:1,
        inspection_status:1,
        status_b:1,
        status_c:1,
      };
    this.materialSubstitutionAuditService.getaudit(this.audit_status).subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        for(let audit of this.dataSet){
          this.dataDetail[audit.audit] = {
            design_note:null,
            matl_note:null,
            welding_note:null,
            process_note:null,
            inspection_note:null,
            b_note:null,
            c_note:null
          };
          this.search(audit.audit);
        }
      }
    })
  }
  Audit(type,audit,result){
    this.audit_result[type] = result;
    this.audit_result.design_note = this.dataDetail[audit].design_note;
    this.audit_result.matl_note = this.dataDetail[audit].matl_note;
    this.audit_result.welding_note = this.dataDetail[audit].welding_note;
    this.audit_result.process_note = this.dataDetail[audit].process_note;
    this.audit_result.inspection_note = this.dataDetail[audit].inspection_note;
    this.audit_result.b_note = this.dataDetail[audit].b_note;
    this.audit_result.c_note = this.dataDetail[audit].c_note;
    this.audit_result[this.audit_user[type]] = this._storage.get("username");
    this.audit_result.audit = audit;
    this.materialSubstitutionAuditService.audit(this.audit_result).subscribe((res)=>{
      if(res["result"]=="success"){
        this.message.success("审核成功！");
        this.searchData();
      }else{
        this.message.error("操作失败，请稍后重试！");
      }
    })
  }

  handleOk(): void {
    if(this.rolename!=null&&this.rolename!=""){
      this._storage.set("substitutionRole",this.rolename);
      this.message.info("已将默认审核身份设置为："+this.rolename+"，下次进入将自动选择！");
      this.isVisible = false;
      this.searchData();
    }
  }
  handleCancle(): void {
    this.message.error("请选择审核身份！");
  }
  changeRole(){
    this.isVisible = true;
    this.rolename = null;
  }
}
