import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductTestBoardDataRegistrationAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchproductplatedata',{
      prodno:null,
      status:0
    })
  }
  Audit(prodno,specimenno,id,status,audit_user){
    return this.http.post(this.api.BASEURL+'/changestatusforpredata',{
      prodno:prodno,
      specimenno:specimenno,
      id:id,
      audit_user:audit_user,
      status:status,
    })
  }
}
