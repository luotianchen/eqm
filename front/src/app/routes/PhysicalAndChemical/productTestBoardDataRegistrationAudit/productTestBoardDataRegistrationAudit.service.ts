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
  Audit(prodno,status,audit_user){
    return this.http.post(this.api.BASEURL+'/auditproducttestboarddata',{
      prodno:prodno,
      audit_user:audit_user,
      status:status,
    })
  }
}
