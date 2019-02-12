import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class WeldingDistributeService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodnocache',{prodno:prodno,status:1})
  }
  putdistribute(data){
    return this.http.post(this.api.BASEURL+"/putpressureparts",data);
  }
  savedistribute(data){
    return this.http.post(`${this.api.BASEURL}/putpressurepartscache`,data);
  }
  getPartsname(){
    return this.http.get(this.api.BASEURL+"/getpartsname");
  }
  addPartsname(partsname,enpartsname){
    return this.http.post(this.api.BASEURL+"/putpartsname",{partsname:partsname,enpartsname:enpartsname});
  }
  getputmaterial() {
    return this.http.get(`${this.api.BASEURL}/getputmaterial`);
  }
  getcodedmarking() {
    return this.http.get(`${this.api.BASEURL}/getcodedmarking`);
  }
  getuserform() {
    return this.http.get(`${this.api.BASEURL}/getuserform`);
  }
}
