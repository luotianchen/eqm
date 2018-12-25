import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class VacuumParameterService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getprodno(){
    return this.http.get(this.api.BASEURL+"/getprodno");
  }
  getdistribute(prodno){
    return this.http.post(this.api.BASEURL+'/searchbyprodno',{prodno:prodno,status:1})
  }
  putdistribute(data){
    console.log(JSON.stringify(data))
    return this.http.post(this.api.BASEURL+"/putpressureparts",data);
  }
  addMatlname(matlname){
    return this.http.post(this.api.BASEURL+"/putmatlname",{matlname:matlname});
  }
  putVacuumParameter(data){
    return this.http.post(this.api.BASEURL+"/putVacuumParameter",data)
  }
}
