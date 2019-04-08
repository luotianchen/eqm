import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class DesignQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(){
    return this.http.get(this.api.BASEURL+"/getdwgnobynoaudit");
  }
  getaudited(){
    return this.http.get(this.api.BASEURL+"/getdwgnoaudited");
  }
  getbydwgno(dwgno,status){
    return this.http.post(this.api.BASEURL+'/searchproparlist',{dwgno:dwgno,status:1})
  }
  getsaferel(dwgno,status){
    return this.http.post(this.api.BASEURL+'/searchsafedisdevice',{dwgno:dwgno,status:1})
  }
  getchannel(dwgno,status){
    return this.http.post(this.api.BASEURL+'/searchchanneldata',{dwgno:dwgno,status:1})
  }
  audit(dwgno,status,audit_user){
    return this.http.post(this.api.BASEURL+"/putproparlistaudit",{dwgno:dwgno,audit:1,audit_user:audit_user});
  }
}
