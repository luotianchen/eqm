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
  public prodnos:any;
  public prodno:any;
  public prodname:any;
  public dwgno:any;
  public status = false;
  userSign = null;
  public dataSet = [];
  public dataSet2 = [];
  public dataSets = [];
  public dataSets2 = [];
  public signs = {};
  rule:any;
  constructor(public fb: FormBuilder, public materialDistributionLedgerService: MaterialDistributionLedgerService) {
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
    this.materialDistributionLedgerService.getindexbymatlcoderules().subscribe(res => {
      if (res['result'] == "success") {
        this.rule = res;
      }
    })
  }
  getSign(username){
    this.materialDistributionLedgerService.getSignImage(username).subscribe(res=>{
      if(res['result'] == "success"){
        if(!this.signs[username])
          this.signs[username] = res['url'];
      }
    })
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid) {
      this.materialDistributionLedgerService.getdistribute(this.validateForm.value.prodno).subscribe((res) => {
        if (res['result'] == "success") {
          if(res['data'].length==0){
            alert('未查询到材料发放数据！');
            return;
          }
          if(res['user'])
            this.materialDistributionLedgerService.getSignImage(res['user']).subscribe(res=>{
              if(res['result'] == "success"){
                this.userSign = res['url']
              }else{
                alert("管理员未上传签名！")
              }
            })
          this.prodno = this.validateForm.value.prodno;
          this.prodname = res['prodname'];
          this.dwgno = res['dwgno'];
          for(let item of res['data']) this.getSign(item.issuematl);
          this.dataSet = [...res['data']].filter(item=>item.codedmarking[this.rule['index']-1] != this.rule['welding']);
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
          this.dataSet2 = [...res['data']].filter(item=>item.codedmarking[this.rule['index']-1] == this.rule['welding']);
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
