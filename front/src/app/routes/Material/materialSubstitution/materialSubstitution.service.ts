import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class MaterialSubstitutionService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  putSubstitution(data){
    return this.http.post(this.api.BASEURL+'/putmatlsubstitution',data)
  }
  getdesignation(){
    return this.http.get(this.api.BASEURL+'/getputmaterial')
  }
  getPartsname(){
    return this.http.get(this.api.BASEURL+"/getpartsname");
  }
  addPartsname(partsname,enpartsname){
    return this.http.post(this.api.BASEURL+"/putpartsname",{partsname:partsname,enpartsname:enpartsname});
  }
}
