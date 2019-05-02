import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class MaterialReinspectionCommissionService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getcodedmarking(codedmarking){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/getcodedmarking",{codedmarking:codedmarking}).subscribe(
        result=>{
          resolve(result);
        }
      )
    });
  }
  putproducttestboardcommission(data){
    return new Promise((resolve, reject) => {
      this.http.post(this.api.BASEURL+"/putrematerialitem",data).subscribe(
        result=>{
          resolve(result);
        }
      )
    });
  }
}
