import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class WarehousingRegistrationService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getputmaterial() {
    return this.http.get(`${this.api.BASEURL}/getputmaterial`);
  }
  getmatlstandbydesignation(designation){
    return this.http.post(`${this.api.BASEURL}/searchmatlstandbydes`,{designation:designation});
  }
  contraststand(matlstand,designation,spec){
    return this.http.post(`${this.api.BASEURL}/contraststand`,{matlstand:matlstand,designation:designation,spec:spec});
  }
  submitForm(data:any){
    return this.http.post(`${this.api.BASEURL}/putmaterial`,data);
  }
  putmaterialcache(data:any){
    return this.http.post(`${this.api.BASEURL}/putmaterialcache`,data);
  }
  getmaterialByCodedmarking(codedmarking){
    return this.http.post(`${this.api.BASEURL}/getmaterialcache`,{codedmarking:codedmarking})
  }
  addMillunit(millunit,millunitename){
    return this.http.post(`${this.api.BASEURL}/putmillunit`,{millunit:millunit,millunitename:millunitename});
  }
  addSupplier(supplier){
    return this.http.post(this.api.BASEURL+"/putsupplier",{supplier:supplier});
  }
  addMatlname(matlname){
    return this.http.post(this.api.BASEURL+"/putmatlname",{matlname:matlname});
  }
  addModelstand(modelstand){
    return this.http.post(this.api.BASEURL+"/putmodelstand",{modelstand:modelstand});
  }

}
