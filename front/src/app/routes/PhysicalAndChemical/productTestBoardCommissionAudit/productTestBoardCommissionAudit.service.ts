import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductTestBoardCommissionAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchprotestboardcom',{
      prodno:null,
      status:0
    })
  }
  Audit(id,prodno,specimenno,status,audit_user){
    return this.http.post(this.api.BASEURL+'/changestatusforprotest',{
      id:id,
      prodno:prodno,
      specimenno:specimenno,
      audit_user:audit_user,
      status:status,
    })
  }
}
