import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class MaterialReinspectionService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getcodedmarking(){
    return new Promise((resolve, reject) => {
      this.http.get(this.api.BASEURL+"/getcodedmarking").subscribe(
        result=>{
          resolve(result);
        }
      )
    });
  }
  searchmatlnotice(codedmarking){
    return new Promise((resolve, reject) =>{
      this.http.post(this.api.BASEURL+"/searchmatlnotice",{codedmarking:codedmarking}).subscribe(
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
