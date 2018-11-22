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
  audit(audit,status){
    return this.http.post(this.api.BASEURL+"/auditpressureAudit",{audit:audit,status:status});
  }
}
