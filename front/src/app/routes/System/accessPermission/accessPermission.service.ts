import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class AccessPermissionService {
  constructor( private http: HttpClient,private apiService:ApiService) {
  }
  getroutepower(){
    return this.http.get("https://www.easy-mock.com/mock/5bd28b54c16e907322bb019e/eqm/getroutepower")
  }
  getrole(){
    return this.http.get(`${this.apiService.BASEURL}/getrole`);
  }
  putroutepower(data){
    return this.http.post("https://www.easy-mock.com/mock/5bd28b54c16e907322bb019e/eqm/putroutepower",{data:data})
  }
}
