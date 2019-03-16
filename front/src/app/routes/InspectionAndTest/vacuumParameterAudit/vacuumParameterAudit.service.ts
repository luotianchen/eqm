import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class VacuumParameterAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(prodno){
    return this.http.post(this.api.BASEURL+"/searchvacuumbystatus",{prodno:prodno,status:0});
  }
  audit(prodno,audit_user,status){//提交真空参数审核
    return this.http.post(this.api.BASEURL+"/changestatusforvacuum",{prodno:prodno,audit_user:audit_user,status:status});
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getauditOk(prodno){
    return this.http.post(this.api.BASEURL+"/searchvacuumbystatus",{prodno:prodno,status:1});
  }
}
