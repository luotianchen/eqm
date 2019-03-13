import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";


@Injectable()
export class MaterialReinspectionQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getAudit(year,codedmarking){
    return this.http.post(this.api.BASEURL+'/searchrematerial',{
      codedmarking:codedmarking,
      year:year,
      status:1
    })
  }
}
