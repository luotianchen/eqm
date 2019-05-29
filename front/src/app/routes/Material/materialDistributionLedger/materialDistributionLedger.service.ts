import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MaterialDistributionLedgerService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  getSignImage(username){
    return this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username})
  }
  getindexbymatlcoderules(){ //获取材料代码信息
    return this.http.get(`${this.api.BASEURL}/getindexbymatlcoderules`)
  }
  getuserform() {
    return this.http.get(`${this.api.BASEURL}/getuserform`);
  }
}
