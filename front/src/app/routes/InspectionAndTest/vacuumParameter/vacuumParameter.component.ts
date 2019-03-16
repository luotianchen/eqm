import {Component, OnInit, TemplateRef} from '@angular/core';
import {VacuumParameterService} from "./vacuumParameter.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-vacuumParameter',
  templateUrl: 'vacuumParameter.component.html',
  styleUrls: ['./vacuumParameter.component.less'],
  providers: [VacuumParameterService]
})
export class VacuumParameterComponent implements OnInit {
  public prodnos:any;
  validateForm: FormGroup;
  dataSet = [];
  users = [];
  num2pa = {
    13:26.6,
    22:13.3,
    25:11.97,
    28:10.64,
    30:9.31,
    33:7.98,
    34:7.74,
    36:7.18,
    38:6.65,
    40:6.118,
    41:5.852,
    42:5.453,
    43:5.32,
    44:5.05,
    45:4.655,
    46:4.52,
    47:4.39,
    48:4.26,
    49:4.12,
    50:3.99,
    51:3.86,
    52:3.591,
    53:3.458,
    54:3.325,
    55:3.192,
    56:3.192,
    57:3.06,
    58:2.926,
    59:2.926,
    60:2.79,
    61:2.66,
    62:2.53,
    63:2.39,
    64:2.26,
    65:2.13,
    66:1.995,
    67:1.995,
    68:1.862,
    69:1.862,
    70:1.729,
    71:1.729,
    72:1.596,
    73:1.596,
    74:1.463,
    75:1.463,
    76:1.33,
    77:1.3,
    78:1.26,
    79:1.2,
    80:1.131,
    81:1.064,
    82:0.998,
    83:0.931,
    84:0.853,
    85:0.77,
    86:0.718,
    87:0.67,
    88:0.6,
    89:0.57,
    90:0.48,
    91:0.44,
    92:0.37,
    93:0.31,
    94:0.266,
    95:0.213,
    96:0.173,
    97:0.133
  }
  ngOnInit(): void {
    this.vacuumParameterService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.vacuumParameterService.getuserform().subscribe(res=>{
      if(res['result'] == "success"){
        this.users = res['data'];
      }
    })
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "type":["格数", [Validators.required]],
      "initnum":[null, [Validators.required]],
      "statnum":[null, [Validators.required]],
      "k":[0.6, [Validators.required]],
      "initpa":[null],
      "statpa":[null],
      "htcurrent":[null, [Validators.required]],
      "initdate":[null, [Validators.required]],
      "enddate":[null, [Validators.required]],
      "sealvacu":[null, [Validators.required]],
      "sealdate":[null, [Validators.required]],
      "testtemp":[null, [Validators.required]],
      "sealtemp":[null, [Validators.required]],
      "vacuop":[null, [Validators.required]],
      "leakoutrate":[null, [Validators.required]],
    });
  }

  searchData(): void {
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.vacuumParameterService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
        }
      })
    }
    this.setLeakoutrate();
  }
  choose(){
    if(this.validateForm.value.type=="格数"){
      this.validateForm.controls['initnum'].setValidators(Validators.required);
      this.validateForm.controls['statnum'].setValidators(Validators.required);
      this.validateForm.controls['initpa'].setValidators(null);
      this.validateForm.controls['statpa'].setValidators(null);
    }else{
      this.validateForm.controls['initnum'].setValidators(null);
      this.validateForm.controls['statnum'].setValidators(null);
      this.validateForm.controls['initpa'].setValidators(Validators.required);
      this.validateForm.controls['statpa'].setValidators(Validators.required);
    }
  }
  constructor(public vacuumParameterService: VacuumParameterService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  formatInDate(control){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
  check(){
    if(this.validateForm.value.initdate!=null){
      if(new Date(this.validateForm.value.initdate) > new Date(this.validateForm.value.enddate)){
        this.validateForm.controls['enddate'].setValue(null);
      }
    }
    this.setLeakoutrate();
  }
  setLeakoutrate(){
    if((this.validateForm.value.type == '格数' && this.validateForm.value.dwgno && this.validateForm.value.initnum && this.validateForm.value.statnum && this.validateForm.value.initdate &&this.validateForm.value.enddate && this.validateForm.value.k)||(this.validateForm.value.type == '压力' && this.validateForm.value.dwgno && this.validateForm.value.initpa && this.validateForm.value.statpa && this.validateForm.value.initdate &&this.validateForm.value.enddate && this.validateForm.value.k)){
      let q = null,
        v = null,
        p1 = null,
        p2 = null,
        k = null,
        t1 = null,
        t2 = null;//q为最终要求的漏放气速率,t1为开始时间（需转化为秒），t2为结束时间（需转化为秒），p1是初始压力（或格数，格数需转换为压力），p2为静止压力（或格数，格数需转换为压力），k为夹层溶剂修正系数，公式：q = (p1 - p2)*v*k/(t1 - t2)，保留九位小数

      if(this.validateForm.value.type == "格数"){
        p1 = this.num2pa[Math.round(this.validateForm.value.initnum)];
        p2 = this.num2pa[Math.round(this.validateForm.value.statnum)];
        if(!p1 || !p2){
          this.message.error("请检查格数信息是否输入正确！");
          return;
        }
      }else{
        p1 = this.validateForm.value.initpa;
        p2 = this.validateForm.value.statpa;
      }
      t1 = new Date(this.validateForm.value.initdate).getTime()/1000;
      t2 = new Date(this.validateForm.value.enddate).getTime()/1000;
      if(t1 == t2){
        this.message.error("请检查开始、结束时间是否输入正确！(除数不能为0)");
        return;
      }
      k = this.validateForm.value.k;
      this.vacuumParameterService.searchChannelData(this.validateForm.value.dwgno).subscribe(res=>{
        if(res['result'] == "success"){
          if(res['data'].length<2){
            this.message.error("请确认该产品拥有两个以上通道！");
            return;
          }
          v = res['data'][1].volume;
          q = ((p1 - p2) * v * k/(t1 - t2));
          this.validateForm.controls['leakoutrate'].setValue(q.toExponential(2));
        }else{
          this.modalService.error({
            nzTitle: '请检查体积',
            nzContent: '请检查图号对应的夹套容积是否正确！'
          })
        }
      });
    }else{
      this.validateForm.controls['leakoutrate'].setValue(null);
    }
  }
  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.vacuumParameterService.putVacuumParameter({
        "prodno":this.validateForm.value.prodno,
        "prodname":this.validateForm.value.prodno,
        "dwgno":this.validateForm.value.dwgno,
        "initnum":this.validateForm.value.initnum,
        "statnum":this.validateForm.value.statnum,
        "initpa":this.validateForm.value.initpa,
        "statpa":this.validateForm.value.statpa,
        "htcurrent":this.validateForm.value.htcurrent,
        "initdate":this.validateForm.value.initdate,
        "enddate":this.validateForm.value.enddate,
        "sealvacu":this.validateForm.value.sealvacu,
        "sealdate":this.validateForm.value.sealdate,
        "testtemp":this.validateForm.value.testtemp,
        "sealtemp":this.validateForm.value.sealtemp,
        "vacuop":this.validateForm.value.vacuop,
        "leakoutrate":this.validateForm.value.leakoutrate,
        "user":this._storage.get("username")
      }).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '提交成功',
            nzContent: '真空参数提交成功！'
          });
          this.validateForm.reset();
        }
      })
    }
  }
}
