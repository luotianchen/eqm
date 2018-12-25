import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductTestBoardCommissionQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchproducttestboardcommission',{
      prodno:null,
      status:0
    })
  }
}
