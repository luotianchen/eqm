import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class MaterialReinspectionCommissionQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(){
    return this.http.post(this.api.BASEURL+'/searchrematerialitem',{
      codedmarking:null,
      status:1
    })
  }
}
