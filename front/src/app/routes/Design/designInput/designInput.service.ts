import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class DesignInputService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getputmaterial(){
    return this.http.get(this.api.BASEURL+"/getputmaterial")
  }
  getprodname(){
    return this.http.get(this.api.BASEURL+"/getprodname");
  }
  getgetwmedias(){
    return this.http.get(this.api.BASEURL+"/getwmedia");
  }
  getstand(){
    return this.http.get(this.api.BASEURL+"/getstand");
  }
  putSaferel(data){
    return this.http.post(this.api.BASEURL+"/putsafedisdevice",data);
  }
  putProduce(data){
    return this.http.post(this.api.BASEURL+"/putproparlist",data);
  }
  putChannel(data){
    return this.http.post(this.api.BASEURL+"/putchanneldata",data)
  }
  putProdname(prodname,ename){
    return this.http.post(this.api.BASEURL+"/putprodname",{prodname:prodname,ename:ename})
  }
  putWmedia(wmedia,wmediaen){
    return this.http.post(this.api.BASEURL+"/putwmedia",{wmedia:wmedia,wmediaen:wmediaen})
  }
}
