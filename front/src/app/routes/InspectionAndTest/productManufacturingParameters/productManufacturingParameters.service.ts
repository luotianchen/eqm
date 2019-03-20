import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductManufacturingParametersService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getdistribute(prodno){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1}).subscribe(result=>{
      resolve(result);
    })
    })
  }
  addOrderunit(orderunit,orderunitename){
    return this.http.post(this.api.BASEURL+"/putorderunit",{orderunit:orderunit,orderunitename:orderunitename})
  }
  getOrderunit(){
    return new Promise((resolve, reject) => {
      this.http.get(this.api.BASEURL+"/getorderunit").subscribe(result=>{
        resolve(result);
      })
    })
  }
  getbydwgno(dwgno){//为了获取设计日期
    return this.http.post(this.api.BASEURL+'/searchproparlist',{dwgno:dwgno,status:1})
  }
  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform");
  }
  getDataStand(dwgno){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/searchdatacontraststand",{dwgno:dwgno}).subscribe(result=>{
        resolve(result);
      })
    })
  }
  getDataStand2(dwgno){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/searchdatacontraststand2",{dwgno:dwgno}).subscribe(result=>{
        resolve(result);
      })
    })
  }
  putManufacturing(data){
    return this.http.post(this.api.BASEURL+"/putpromanparlist",data);
  }
  putManufacturing2(data){
    return this.http.post(this.api.BASEURL+"/putpromanparlist2",data);
  }
  getdwgno1and2(prodno){
    return this.http.post(this.api.BASEURL+"/searchdwgnoot",{prodno:prodno});
  }
  getSaferel(dwgno){
    return this.http.post(this.api.BASEURL+"/searchsafedisdevice",{dwgno:dwgno,status:1});
  }
  updateSaferelMFUnit(data){
    return this.http.post(this.api.BASEURL+"/updatesafedisdevice",data);
  }
  getputmaterial() {
    return this.http.get(`${this.api.BASEURL}/getputmaterial`);
  }
}
