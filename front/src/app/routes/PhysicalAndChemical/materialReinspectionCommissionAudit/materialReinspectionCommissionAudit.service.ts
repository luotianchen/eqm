import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class MaterialReinspectionCommissionAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchrematerialitem',{
      codedmarking:null,
      status:0
    })
  }
  Audit(codedmarking,status,audit_user){
    return this.http.post(this.api.BASEURL+'/changestatusforrema',{
      codedmarking:codedmarking,
      audit_user:audit_user,
      status:status,
    })
  }
}
