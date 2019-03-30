import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class TestParametersService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getdwgno(){
    return this.http.get(this.api.BASEURL+"/getdwgnoaudited");
  }
  putPressureTest(data){
    return this.http.post(this.api.BASEURL+'/putprenotiform',data)
  }
  getPressandLeak(prodno){
    return this.http.post(this.api.BASEURL+"/searchpreandleaknew",{prodno:prodno});
  }
  getexitno(){
    return this.http.get(this.api.BASEURL+"/getexitno")
  }
  putpreandleak(data){
    return this.http.post(this.api.BASEURL+"/putpreandleak",data);
  }
  check(prodno){//检查产品编号与图号连接状态
    return this.http.post(this.api.BASEURL+"/checkproanddwg",{
      prodno:prodno
    })
  }
  unlinkProdnoandDwgno(prodno,dwgno){//解除图号、产品编号连接
    return this.http.post(this.api.BASEURL+'/deleteproparlist',{
      prodno:prodno,
      dwgno:dwgno
    })
  }
  searchmeabyexi(exitno,date){
    return this.http.post(`${this.api.BASEURL}/searchmeabyexi`,{exitno:exitno,date:date});
  }
}
