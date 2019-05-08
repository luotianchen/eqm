import {Component, ElementRef, OnInit} from '@angular/core';
import {SessionStorageService} from 'src/app/core/storage/storage.module';
import {DashboardService} from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['./dashboard.component.less'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  constructor(public _storage: SessionStorageService, private dashboard: DashboardService) {
  }
  grateSentence = "您好";
  loading = true;
  dataModel:any = {
    result:"success",
    matl:0,//材料入库+材料代用+材料发放
    matlin:0,//材料入库
    matlsubs:0,//材料代用
    matldist:0,//材料发放
    pyhandche:0,//材料复验申请+复验登记+产品试板委托申请+产品试板数据登记
    matlreincom:0,//材料复验申请
    matlrein:0,//材料复验登记
    testboardcom:0,//产品试板委托申请
    testboard:0,//产品试板数据登记
    inspecandtest:0,//真空参数+产品制造参数
    vacuum:0,//真空参数
    productmanu:0,//产品制造参数
    measinstru:0,//计量台账
    design:0,//设计
  };

  getGrate(){
    let day  = new Date();
    let time = day.getHours();
    let sentence = "";
    if(time<9 && time>=2)
      sentence = "早上好！";
    else if(time<12 && time>=10)
      sentence = "上午好！";
    else if(time==12)
      sentence = "中午好！";
    else if(time>=13 && time<=18)
      sentence = "下午好！";
    else
      sentence = "晚上好！";
    this.grateSentence = sentence;
    window.setTimeout(this.getGrate,1000 * 60);
  }
  ngOnInit() {
    this.dashboard.getinfo().subscribe(res=>{
      this.loading = false;
      if(res['result'] == "success"){
        this.dataModel = res;
      }
    });
    this.getGrate();
    window.setTimeout(this.getGrate,1000 * 60);
  }
}
