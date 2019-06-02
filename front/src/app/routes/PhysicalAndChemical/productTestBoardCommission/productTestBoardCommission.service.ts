import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from '../../../core/api/api.service';


@Injectable()
export class ProductTestBoardCommissionService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  getUsers(){
    return this.http.get(this.api.BASEURL+"/getuserform");
  }
  getDesignationAndSpec(prodno){
    return this.http.post(this.api.BASEURL+"/searchspecanddeinpre",{prodno:prodno});
  }
  putproducttestboardcommission(data){
    return this.http.post(this.api.BASEURL+"/putprotestboardcom",data);
  }
}
