import {Component, OnInit} from '@angular/core';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";
import {PressurePartsReportService} from './pressurePartsReport.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-pressurePartsReport',
  templateUrl: 'pressurePartsReport.component.html',
  styleUrls: ['./pressurePartsReport.component.less'],
  providers: [PressurePartsReportService]
})

export class PressurePartsReportComponent implements OnInit {
  validateForm: FormGroup;
  prodnos = [];
  prodno = "";
  reportData:object = {
    issuematl:"",
    prodname: "",
    ename: "",
    issuedate:"",
    audit_user: "",
    data:[]
  };
  reportDatas = [];
  constructor(public fb: FormBuilder, public pressurePartsReportService: PressurePartsReportService,public msg:NzMessageService) {
  }
  ngOnInit(): void {
    this.pressurePartsReportService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    });
  }
  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.prodno = this.validateForm.value.prodno;
      this.pressurePartsReportService.putdistribute(this.validateForm.value.prodno).subscribe((res)=>{
        if(res['result']=="success"){
          if(res['data'].length>0){
            this.reportData = res;
            this.pressurePartsReportService.getSignImage(this.reportData['issuematl']).then((res)=>{
              if(res['result']=="success"){
                this.reportData['issuematl'] = res['url'];
                this.pressurePartsReportService.getSignImage(this.reportData['audit_user']).then((res)=>{
                  if(res['result']=="success"){
                    this.reportData['audit_user'] = res['url'];
                    let num = Math.ceil(this.reportData['data'].length/19)*19;
                    for(let i =0;i<num;i++){
                      if(this.reportData['data'][i]){
                        this.reportData['data'][i].index = i+1;
                      }else{
                        this.reportData['data'][i] = {};
                      }
                    }
                    this.reportDatas = [];
                    while(this.reportData['data'].length>0){
                      this.reportDatas.push({
                        issuematl:this.reportData['issuematl'],
                        prodname: this.reportData['prodname'],
                        ename: this.reportData['ename'],
                        issuedate:this.reportData['issuedate'],
                        audit_user: this.reportData['audit_user'],
                        data:this.reportData['data'].slice(0,19)
                      });
                      this.reportData['data'] = this.reportData['data'].slice(19);
                    }
                    console.log(this.reportDatas);
                  }else{
                    this.msg.error("请检查材料责任人"+this.reportData['audit_user']+"是否正确上传签名！");
                  }
                });
              }else{
                this.msg.error("请检查填表人"+this.reportData['issuematl']+"是否正确上传签名！");
              }
            });//使用promise来实现同步操作
          }
          else
            this.msg.error("未查到受压元件材料记录！")
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
