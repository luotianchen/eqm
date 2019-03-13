import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class AccessPermissionService {
  constructor( private http: HttpClient,private apiService:ApiService) {
  }
  getroutepower(){
    return this.http.get(this.apiService.BASEURL+"/getroutepower")
  }
  getrole(){
    return this.http.get(`${this.apiService.BASEURL}/getrole`);
  }
  putroutepower(data){
    return this.http.post(this.apiService.BASEURL+"/putroutepower",{data:data})
  }
}
