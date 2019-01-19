import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class TestParametersAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getPressureTest(){
    return this.http.post(this.api.BASEURL+'/getpressuretest',{
      prodno:null,
      status:0,
      ppart:null
    })
  }
  Audit(prodno,ppart,status,audit_user){
    return this.http.post(this.api.BASEURL+'/auditpressuretest',{
      prodno:prodno,
      ppart:ppart,
      status:status,
      audit_user:audit_user
    })
  }
}
