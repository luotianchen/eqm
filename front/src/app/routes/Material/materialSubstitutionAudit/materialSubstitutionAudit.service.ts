import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MaterialSubstitutionAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(data){
    return this.http.post(this.api.BASEURL+"/searchsubstitutestatus",data);
  }
  getSubstitution(audit){
    return this.http.post(this.api.BASEURL+'/searchbystatus',{audit:audit})
  }
  audit(data){
    return this.http.post(this.api.BASEURL+"/putstatusintosubstitution",data);
  }
}
