import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class PressurePartsAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(){
    return this.http.post(this.api.BASEURL+"/searchprebystatus",{status:0});
  }
  getdistribute(audit){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{audit:audit,status:0})
  }
  audit(audit,status,audit_user){
    return this.http.post(this.api.BASEURL+"/updateprestatus",{audit:audit,status:status,audit_user:audit_user});
  }
  getuserform() {
    return this.http.get(`${this.api.BASEURL}/getuserform`);
  }
}
