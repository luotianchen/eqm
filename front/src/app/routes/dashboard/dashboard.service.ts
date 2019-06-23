import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ApiService} from "../../core/api/api.service";


@Injectable()
export class DashboardService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getinfo(){
    return this.http.get(this.api.BASEURL+"/getdashboardinfo");
  }
  getHistory(){
    return this.http.get(this.api.BASEURL+"/gethistory");
  }
}
