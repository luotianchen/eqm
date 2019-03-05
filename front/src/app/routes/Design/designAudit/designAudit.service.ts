import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class DesignAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(){
    return this.http.get(this.api.BASEURL+"/getdwgnobynoaudit");
  }
  getaudited(){
    return this.http.get(this.api.BASEURL+"/getdwgnoaudited");
  }
  getbydwgno(dwgno){
    return this.http.post(this.api.BASEURL+'/searchproparlist',{dwgno:dwgno})
  }
  getsaferel(dwgno){
    return this.http.post(this.api.BASEURL+'/searchsafedisdevice',{dwgno:dwgno})
  }
  getchannel(dwgno){
    return this.http.post(this.api.BASEURL+'/searchchanneldata',{dwgno:dwgno})
  }
  audit(dwgno,status,audit_user){
    return this.http.post(this.api.BASEURL+"/putproparlistaudit",{dwgno:dwgno,audit:status,audit_user:audit_user});
  }
}
