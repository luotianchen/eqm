import {Component, OnInit} from '@angular/core';
import {MaterialDistributionLedgerService} from './materialDistributionLedger.service';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-materialDistributionLedger',
  templateUrl: 'materialDistributionLedger.component.html',
  styleUrls: ['./materialDistributionLedger.component.less'],
  providers: [MaterialDistributionLedgerService]
})
export class MaterialDistributionLedgerComponent implements OnInit {
  validateForm: FormGroup;
  private prodnos:any;
  private prodno:any;
  private prodname:any;
  private dwgno:any;
  private status = false;
  private dataSet = [];
  private dataSet2 = [];
  private dataSets = [];
  private dataSets2 = [];
  constructor(private fb: FormBuilder, private materialDistributionLedgerService: MaterialDistributionLedgerService) {
  }
  ngOnInit(): void {
    this.materialDistributionLedgerService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
    });
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid) {
      this.materialDistributionLedgerService.getdistribute(this.validateForm.value.prodno).subscribe((res) => {
        if (res['result'] == "success") {
          this.prodno = this.validateForm.value.prodno;
          this.prodname = res['prodname'];
          this.dwgno = res['dwgno'];
          this.dataSet = [...res['data']];
          //对于普通发放台帐
          let num = Math.ceil(this.dataSet.length/22)*22;
          for(let i=0;i<num;i++){
            if(this.dataSet[i])
              this.dataSet[i].index = i+1;
            else
              this.dataSet[i] = {index:""};
          }
          this.dataSets = [];
          while(this.dataSet.length>0){
            this.dataSets.push(this.dataSet.slice(0,22));
            this.dataSet = this.dataSet.slice(22);
          }
          //对于焊材
          this.dataSet2 = [...res['data']];
          let num2 = Math.ceil(this.dataSet2.length/25)*25;
          for(let i=0;i<num2;i++){
            if(this.dataSet2[i])
              this.dataSet2[i].index = i+1;
            else
              this.dataSet2[i] = {index:""};
          }
          this.dataSets2 = [];
          while(this.dataSet2.length>0){
            this.dataSets2.push(this.dataSet2.slice(0,25));
            this.dataSet2 = this.dataSet2.slice(25);
          }
          this.status = true;
        }
      })
    }
  }
  printCSS: string[];
  printStyle: string;
  printBtnBoolean = true;
  printComplete() {
    this.printBtnBoolean = true;
  }
  beforePrint() {
    this.printBtnBoolean = false;
  }
}
