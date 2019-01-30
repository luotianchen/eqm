import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class SettingService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getSettingInfo(){
    return this.http.get(this.api.BASEURL+"/getsettinginfo");
  }
  putSettingInfo(data){
    return this.http.post(this.api.BASEURL+"/putsettinginfo",data);
  }
}
