import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class ProductTestBoardDataRegistrationService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  gettestboardstand(){
    return this.http.get(this.api.BASEURL+"/gettestboardstand");
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  addtestboardstand(testboardstand){
    return this.http.post(this.api.BASEURL+"/addtestboardstand",{testboardstand:testboardstand})
  }
  getputmaterial(){
    return this.http.get(this.api.BASEURL+"/getputmaterial");
  }
  getdatarange(prodno,matlstand,designation){
    return this.http.post(this.api.BASEURL+"/getdatarange",{
      prodno:prodno,
      matlstand:matlstand,
      designation:designation
    })
  }
  puttestboardparam(data){
    return this.http.post(this.api.BASEURL+"/puttestboardparam",data);
  }
}
