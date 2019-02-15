import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MeasuringInstrumentLedgerAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(){
    return this.http.post(this.api.BASEURL+"/searchpregaubystatus",{status:0});
  }
  audit(id,status,audit_user){
    return this.http.post(this.api.BASEURL+"/changestatusforpregau",{id:id,status:status,audit_user:audit_user});
  }
}
