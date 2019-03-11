import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class MaterialReinspectionService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getcodedmarking(){ //复验申请审核通过的codedmarking
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/searchrematerialitem",{status:1}).subscribe(
        result=>{
          resolve(result);
        }
      )
    });
  }
  searchrematerialitem(codedmarking){
    return this.http.post(`${this.api.BASEURL}/searchrematerialitem`,{codedmarking:codedmarking,status:1})
  }
  searchmatlnotice(codedmarking){
    return new Promise((resolve, reject) =>{
      this.http.post(this.api.BASEURL+"/getmaterial",{codedmarking:codedmarking}).subscribe(
        result=>{
          resolve(result);
        }
      )
    })
  }
  putMatlReinspection(data){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/putrematerial",data).subscribe(
        result=>{
          resolve(result);
        }
      )
    });
  }
  contraststand(matlstand,designation,spec){
    return this.http.post(`${this.api.BASEURL}/contraststand`,{matlstand:matlstand,designation:designation,spec:spec});
  }
}
