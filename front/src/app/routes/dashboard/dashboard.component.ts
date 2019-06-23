import {Component, ElementRef, OnInit} from '@angular/core';
import {SessionStorageService} from 'src/app/core/storage/storage.module';
import {DashboardService} from './dashboard.service';
import {Router} from "@angular/router";
import {SettingsService} from "../../core/services/settings.service";
import {MenuService} from "../../core/services/menu.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['./dashboard.component.less'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  constructor(public _storage: SessionStorageService, public menu: MenuService, private dashboard: DashboardService,public router:Router,public settings: SettingsService) {
    document.getElementsByTagName('body')[0].style.minWidth = 900+"px";
    document.getElementsByTagName('html')[0].style.minWidth = 900+"px";
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
    else if(time<12 && time>=9)
      sentence = "上午好！";
    else if(time==12)
      sentence = "中午好！";
    else if(time>13 && time<=18)
      sentence = "下午好！";
    else
      sentence = "晚上好！";
    this.grateSentence = sentence;
    window.setTimeout(this.getGrate,1000 * 60);
  }

  public history = null;
  public time = null;
  ngOnInit() {
    this.dashboard.getinfo().subscribe(res=>{
      this.loading = false;
      if(res['result'] == "success"){
        this.dataModel = res;
      }
    });
    this.dashboard.getHistory().subscribe(res=>{
      if(res['result'] == 'success'){
        this.history = res['message'];
        let date = res['date'].split('-');
        this.time = "公元"+date[0]+'年'+date[1]+"月"+date[2]+"日 "+res['lunar'];
      }
    })
    this.getGrate();
    window.setTimeout(this.getGrate,1000 * 60);
  }


  updateMenu(){
    setTimeout(() => {
      this.menu.getMenu().then((result: any) => {
        this.setmenulist(result.data);
        let flag: boolean = true;
        for (let sider of this.menulist) {
          for (let item of sider.data) {
            item.selected = item.route == this.router.url;
            if (item.route == this.router.url) flag = false;
            let submenu = item['submenu'];
            if (!!submenu) {
              for (let sub of submenu) {
                sub.selected = sub.route == this.router.url;
                if(sub.route == this.router.url){
                  flag = false;
                  this.settings.setnav(sider.name);
                  this.setmenuOpenMapTrue(item.name,true);
                }
              }
            }
          }
        }
        if (flag || this.router.url =="/dashboard" || this.router.url =="/") {
          for (let sider of this.menulist) {
            sider.data[0].selected = true;
          }
          if(this.menulist.length>0) this.settings.setnav(this.menulist[0]['name']);
        }
      });
    },500)
  }

  oepnMenuOpenMapHandle(name,value){
    this.settings.oepnMenuOpenMapHandle(name,value);
  }

  openHandler(value: string): void {
    for (const key in this.menuOpenMap) {
      if (key !== value) {
        this.oepnMenuOpenMapHandle(key,false);
      }
    }
  }

  get menulist() {
    return this.settings.menulist;
  }

  setmenulist(o) {
    this.settings.setmenulist(o);
  }

  get menuOpenMap() {
    return this.settings.menuOpenMap;
  }

  setmenuOpenMap(o) {
    this.settings.setmenuOpenMap(o);
  }
  setmenuOpenMapTrue(name,value){
    this.settings.oepnMenuOpenMapHandle(name,value);
  }

}
