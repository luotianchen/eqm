import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";
import {filter} from "rxjs/internal/operators";

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
  putMatlcodeRules(data){
    return this.http.post(this.api.BASEURL+"/putmatlcoderules",data);
  }
  getMatlcodeRules(){
    return this.http.get(`${this.api.BASEURL}/getindexbymatlcoderules`)
  }

  getLogo(){
    return this.http.get(this.api.BASEURL+"/getlogo")
  }
  uploadImg(formData){
    const req = new HttpRequest('POST', this.api.BASEURL+'/putlogo' , formData, {
      // reportProgress: true
    });
    return this.http
      .request(req)
      .pipe(filter(e => e instanceof HttpResponse));
  }
}
