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
  getchannel(dwgno){
    return this.http.post(this.api.BASEURL+'/searchchanneldata',{dwgno:dwgno});
  }
  putPressureTest(data){
    return this.http.post(this.api.BASEURL+'/putprenotiform',data)
  }
  getPressandLeak(prodno,ppart,datetype){
    return this.http.post(this.api.BASEURL+"/searchpreandleak",{prodno:prodno,ppart:ppart,datetype:datetype});
  }
  getexitno(){
    return this.http.get(this.api.BASEURL+"/getexitno")
  }
  gettestpressurebyprodnoanddwgno(prodno,dwgno,ppart){
    return this.http.post(this.api.BASEURL+"/searchprenotibynodw",{prodno:prodno,dwgno:dwgno,ppart:ppart});
  }
  putpreandleak(data){
    return this.http.post(this.api.BASEURL+"/putpreandleak",data);
  }
}
