import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class PressurePartsService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getpressureparts(pageindex,pagesize,prodno) {
    return this.http.post(this.api.BASEURL+'/searchpressurepart',{pageindex:pageindex,pagesize:pagesize,prodno:prodno});
  }
}
