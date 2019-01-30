import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class MaterialReinspectionAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchrematerial',{
      codedmarking:null,
      status:0
    })
  }
  Audit(codedmarking,status,audit_user){
    return this.http.post(this.api.BASEURL+'/changestatusforrematl',{
      codedmarking:codedmarking,
      audit_user:audit_user,
      status:status,
    })
  }
}
