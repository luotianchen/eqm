import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MaterialSubstitutionQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  searchallmaterial(pageindex,pagesize,prodno){
    return this.http.post(this.api.BASEURL+"/getSubstitutes",{
      pageindex:pageindex,
      pagesize:pagesize,
      prodno:prodno
    })
  }
  getSubstitutionByAudit(audit){
    return this.http.post(this.api.BASEURL+'/getSubstitutionByAudit',{audit:audit})
  }
  getByProdno(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  getSignImage(username){
    return this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username})
  }
}
