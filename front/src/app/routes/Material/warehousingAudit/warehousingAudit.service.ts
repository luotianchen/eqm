import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarehousingAuditService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getputmaterial() {
    return this.http.get(this.api.BASEURL+'/getputmaterial');
  }
  searchallmaterial(pageindex,pagesize,codedmarking,matlname,designation,spec,millunit,indate){
    return this.http.post(this.api.BASEURL+"/searchallmaterial",{
      pageindex:pageindex,
      pagesize:pagesize,
      searchdata:{
        codedmarking:codedmarking,
        matlname:matlname,
        designation:designation,
        spec:spec,
        millunit:millunit,
        indate:indate,
        status:0
      }
    })
  }
  audit(codedmarking,status,audit_user){
    return this.http.post(this.api.BASEURL+"/putaudit",{codedmarking:codedmarking,status:status,audit_user:audit_user})
  }
}
