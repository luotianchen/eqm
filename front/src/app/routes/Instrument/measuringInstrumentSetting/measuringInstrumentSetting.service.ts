import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MeasuringInstrumentSettingService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getMeasSetting(){
    return this.http.get(this.api.BASEURL+"/getemail");
  }
  putMeasSetting(data){
    return this.http.post(this.api.BASEURL+"/putemail",data);
  }
  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform")
  }
  testEmail(username,password){
    return this.http.post(this.api.BASEURL+"/testemail",{username:username,password:password});
  }
}
