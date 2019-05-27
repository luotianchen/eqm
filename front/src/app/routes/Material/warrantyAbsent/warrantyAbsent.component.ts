import {Component, OnInit} from '@angular/core';
import {WarrantyAbsentService} from './warrantyAbsent.service';

@Component({
  selector: 'app-warrantyAbsent',
  templateUrl: 'warrantyAbsent.component.html',
  styleUrls: ['./warrantyAbsent.component.less'],
  providers: [WarrantyAbsentService]
})
export class WarrantyAbsentComponent implements OnInit {
  public pageindex = 1;
  public pagesize = 25;
  public dataset = [];
  public dataSetDisplay = [];
  public total = 0;
  public loading = true;
  public searchheatbatchno = '';
  public searchwarrantyno = '';
  public searchcodedmarking = '';
  public searchsupplier = '';

  ngOnInit(): void {
    this.searchData();
  }
  constructor(public warrantyAbsentService: WarrantyAbsentService){
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
        this.dataSetDisplay = this.dataset.slice();
        this.loading = false;
      }
    });
  }

  download(){
    this.warrantyAbsentService.download().subscribe((res:any)=>{
      let blob = new Blob([res]);
      let objectUrl = URL.createObjectURL(blob);
      let a = document.createElement('a');
      let date = new Date();
      document.body.appendChild(a);
      a.setAttribute('style', 'display:none');
      a.setAttribute('href', objectUrl);
      a.setAttribute('download', "质保书未到"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xls");
      a.click();
      URL.revokeObjectURL(objectUrl);
    });
  }

  searchHeatbatchno(): void { //炉批号筛选
    this.dataSetDisplay = this.dataset.filter(item => !!item.heatbatchno && item.heatbatchno.indexOf(this.searchheatbatchno) !== -1);
  }

  searchWarrantyno(): void { //质保书号筛选
    this.dataSetDisplay = this.dataset.filter(item => !!item.warrantyno && item.warrantyno.indexOf(this.searchwarrantyno) !== -1);
  }

  searchCodedmarking(): void{ //入库编号筛选
    this.dataSetDisplay = this.dataset.filter(item=>!!item.codedmarking && item.codedmarking.indexOf(this.searchcodedmarking) !== -1)
  }

  searchSupplier(): void {
    this.dataSetDisplay = this.dataset.filter(item=>!!item.supplier && item.supplier.indexOf(this.searchsupplier) !== -1)
  }

}
