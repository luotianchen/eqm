import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class MaterialReinspectionCommissionService {
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
