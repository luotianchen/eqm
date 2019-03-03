import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class pressTestReportService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  searchpresstest(prodno){//压力试验通知单
    return this.http.post(this.api.BASEURL+"/searchpresstest",{prodno:prodno});
  }
  getSignImage(username){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username}).subscribe(result=>{
        resolve(result);
      })
    })
  }
}

