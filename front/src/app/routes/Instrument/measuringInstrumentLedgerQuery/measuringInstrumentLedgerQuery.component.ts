import {Component, OnInit} from '@angular/core';
import {NzMessageService} from "ng-zorro-antd";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {MeasuringInstrumentLedgerQueryService} from './measuringInstrumentLedgerQuery.service';

@Component({
  selector: 'app-measuringInstrumentLedgerQuery',
  templateUrl: 'measuringInstrumentLedgerQuery.component.html',
  styleUrls: ['./measuringInstrumentLedgerQuery.component.less'],
  providers: [MeasuringInstrumentLedgerQueryService]
})
export class MeasuringInstrumentLedgerQueryComponent implements OnInit {
  private dataSet:any;
  loading = true;
  constructor(private measuringInstrumentLedgerQueryService:MeasuringInstrumentLedgerQueryService,private message : NzMessageService,private _storage:SessionStorageService){
  }
  ngOnInit(): void {
    this.searchData();
    this.loading = false;
  }
  searchData(){
    this.measuringInstrumentLedgerQueryService.getaudit().subscribe((res)=>{
      if(res['result']=="success"){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data['expand'] = true;
        }
      }
    })
  }
}
