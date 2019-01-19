import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class PressurePartsReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  putdistribute(prodno){
    return this.http.post(this.api.BASEURL+"/searchprematl",{prodno:prodno});
  }
  getSignImage(username){
    return this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username})
  }
}
