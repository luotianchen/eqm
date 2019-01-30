import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductManufacturingParametersAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchpromanparlist',{
      prodno:null,
      status:0,
    })
  }
  getAudit2(){
    return this.http.post(this.api.BASEURL+'/searchpromanparlist2',{
      prodno:null,
      dwgno:null,
      status:0,
    })
  }
  Audit(prodno,status,audit_user){
    return this.http.post(this.api.BASEURL+'/audit1',{
      prodno:prodno,
      status:status,
      audit_user:audit_user
    })
  }
  Audit2(prodno,dwgno,status,audit_user){
    return this.http.post(this.api.BASEURL+'/audit2',{
      prodno:prodno,
      dwgno:dwgno,
      status:status,
      audit_user:audit_user
    })
  }
}
