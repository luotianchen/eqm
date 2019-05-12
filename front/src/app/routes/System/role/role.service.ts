import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class RoleService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform");
  }
  getRoles(){
    return this.http.get(this.api.BASEURL+"/getrole");
  }
  getDepartments(){
    return this.http.get(this.api.BASEURL+"/getdepartment");
  }
  changeDepartmentName(department,departmentname){
    return this.http.post(this.api.BASEURL+"/changedepartmentname",{department:department,departmentname:departmentname});
  }
  deleteDepartment(department){
    return this.http.post(this.api.BASEURL+"/deletedepartment",{department:department});
  }
  addRole(department,rolename){
    return this.http.post(this.api.BASEURL+"/putrole",{department:department,rolename:rolename});
  }
  addDepartment(departmentname){
    return this.http.post(this.api.BASEURL+"/putdepartment",{departmentname:departmentname});
  }
  changeRoleName(role,rolename){
    return this.http.post(this.api.BASEURL+"/changerolename",{role:role,rolename:rolename});
  }
  deleteRole(role){
    return this.http.post(this.api.BASEURL+"/deleterole",{role:role});
  }
  moveRole(role,department){
    return this.http.post(this.api.BASEURL+"/changedepartmentbyrole",{role:role,department:department});
  }
}
