import {Component, OnInit} from '@angular/core';
import {WarrantyAbsentService} from './warrantyAbsent.service';

@Component({
  selector: 'app-warrantyAbsent',
  templateUrl: 'warrantyAbsent.component.html',
  styleUrls: ['./warrantyAbsent.component.less'],
  providers: [WarrantyAbsentService]
})
export class WarrantyAbsentComponent implements OnInit {
  private pageindex = 1;
  private pagesize = 25;
  private dataset = [];
  private total = 0;
  private loading = true;

  ngOnInit(): void {
    this.searchData();
  }
  constructor(private warrantyAbsentService: WarrantyAbsentService){
  }
  searchData(reset: boolean = false): void {
    if (reset) {
      this.pageindex = 1;
    }
    this.loading = true;
    this.warrantyAbsentService.getAbsentData(this.pageindex,this.pagesize).subscribe((res)=>{
      if(res["result"]=="success"){
        this.total = res["total"];
        this.dataset = res["data"];
        this.loading = false;
      }
    });
  }
  download(){
    this.warrantyAbsentService.download();
  }}
