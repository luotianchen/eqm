import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class ProductTestBoardDataRegistrationQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchproductplatedata',{
      prodno:null,
      status:1
    })
  }
}
