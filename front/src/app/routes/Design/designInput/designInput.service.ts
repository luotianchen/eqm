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
    return this.http.get(this.api.BASEURL+"/getprostand");
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
  getDeconame(){
    return new Promise(((resolve, reject) =>
      this.http.get(this.api.BASEURL+"/getdeconames").subscribe((result)=>{
      resolve(result)
      })
    ))
  }
  putDeco(data){
    return this.http.post(this.api.BASEURL+"/putdeco",data);
  }
  searchdesbydec(deconame){
    return this.http.post(this.api.BASEURL+"/searchdesbydec",{deconame:deconame});
  }

  //以下为缓存相关接口

  saveSaferel(data){
    return this.http.post(this.api.BASEURL+"/putsafedisdevicecache",data);
  }
  saveProduce(data){
    return this.http.post(this.api.BASEURL+"/putproparlistcache",data);
  }
  saveChannel(data){
    return this.http.post(this.api.BASEURL+"/putchanneldatacache",data)
  }

  getbydwgno(dwgno){
    return this.http.post(this.api.BASEURL+'/searchproparlistcache',{dwgno:dwgno})
  }
  getsaferel(dwgno){
    return this.http.post(this.api.BASEURL+'/searchsafedisdevicecache',{dwgno:dwgno})
  }
  getchannel(dwgno){
    return this.http.post(this.api.BASEURL+'/searchchanneldatacache',{dwgno:dwgno})
  }
}
