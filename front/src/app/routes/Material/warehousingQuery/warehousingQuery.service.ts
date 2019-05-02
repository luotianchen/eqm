import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarehousingQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getputmaterial() {
    return this.http.get(this.api.BASEURL+'/getputmaterial');
  }
  getCodedmarking(codedmarking){
    return this.http.post(this.api.BASEURL+"/getcodedmarking",{codedmarking:codedmarking});
  }
  searchallmaterial(pageindex,pagesize,codedmarking,matlname,designation,spec,millunit,indate,status){
    return this.http.post(this.api.BASEURL+"/searchallmaterial",{
      pageindex:pageindex,
      pagesize:pagesize,
      searchdata:{
        codedmarking:codedmarking,
        matlname:matlname,
        designation:designation,
        spec:spec,
        millunit:millunit,
        indate:indate,
        status:status
      }
    })
  }
}
