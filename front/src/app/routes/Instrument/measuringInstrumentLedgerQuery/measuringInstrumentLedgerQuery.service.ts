import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiService} from "../../../core/api/api.service";

@Injectable()
export class MeasuringInstrumentLedgerQueryService {
  constructor( private http: HttpClient,private api:ApiService) {
  }
  getaudit(gaugeno,exitno,calibdate){
    return this.http.post(this.api.BASEURL+"/searchpregaubystatus",{status:1,gaugeno:gaugeno,exitno:exitno,calibdate:calibdate});
  }
}
