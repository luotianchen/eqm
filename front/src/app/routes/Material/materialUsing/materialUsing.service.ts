import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MaterialUsingService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getUsingData(pageindex,pagesize,codedmarking){
    return this.http.post(this.api.BASEURL+"/searchusematerialresult",{pageindex:pageindex,pagesize:pagesize,codedmarking:codedmarking});
  }
  download(codedmarking){
    return this.http.post(this.api.BASEURL+"/searchusematerialexcel",{codedmarking:codedmarking});
  }
}

