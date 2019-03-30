import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class ProductTestBoardDataRegistrationService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getspecimenno(){//获取试板委托通过但登记未审核通过的试样编号（试件编号）（specimenno）
    return this.http.get(this.api.BASEURL+"/getspecimenno");
  }
  getputmaterial() {
    return this.http.get(`${this.api.BASEURL}/getputmaterial`);
  }
  gettestboardstand(){
    return this.http.get(this.api.BASEURL+"/gettestboardstand");
  }
  addtestboardstand(testboardstand){
    return this.http.post(this.api.BASEURL+"/puttestboardstand",{testboardstand:testboardstand})
  }
  gettestno(testno){
    return this.http.post(this.api.BASEURL+"/gettestno",{testno:testno});
  }
  getdatarange(prodno,matlstand,designation){
    return this.http.post(this.api.BASEURL+"/getdatarange",{
      prodno:prodno,
      matlstand:matlstand,
      designation:designation
    })
  }
  puttestboardparam(data){
    return this.http.post(this.api.BASEURL+"/putproductplatedata",data);
  }
  getSignImage(username){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/getsignatureurl",{username:username}).subscribe(result=>{
        resolve(result);
      })
    })
  }
  getmatlstandbydesignation(designation){
    return this.http.post(`${this.api.BASEURL}/searchmatlstandbydes`,{designation:designation});
  }
}
