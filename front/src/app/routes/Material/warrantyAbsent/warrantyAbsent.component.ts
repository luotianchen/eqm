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
  public dataSet = [];
  public total = 0;
  public loading = true;
  public searchheatbatchno = null;
  public searchwarrantyno = null;
  public searchcodedmarking = null;
  public searchsupplier = null;

  ngOnInit(): void {
  }
  constructor(public warrantyAbsentService: WarrantyAbsentService){
    this.searchData();
  }
  searchData(reset: boolean = false): void {
    if (reset) {
      this.pageindex = 1;
    }
    this.loading = true;
    let searchData = {
      heatbatchno:this.searchheatbatchno,
      warrantyno:this.searchwarrantyno,
      codedmarking:this.searchcodedmarking,
      supplier:this.searchsupplier
    }
    this.warrantyAbsentService.getAbsentData(this.pageindex,this.pagesize,searchData).subscribe((res)=>{
      if(res["result"]=="success"){
        this.total = res["total"];
        this.dataSet = res["data"];
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

}
