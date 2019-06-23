import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {SettingsService} from '../../core/services/settings.service';
import {MenuService} from '../../core/services/menu.service';
import {SessionStorageService} from 'src/app/core/storage/storage.module';
import {NzMessageService} from "ng-zorro-antd";
import { HttpClient } from '@angular/common/http';
import {ApiService} from "../../core/api/api.service";
@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['./header.component.less'],
})

export class HeaderComponent {
  navs = null;
  searchstatus = false;
  flag = true;
  powers = {};
  theme = this.settings.layout.isDark;
  roles = [];
  constructor(public settings: SettingsService, public menu: MenuService, public router: Router, public _storage: SessionStorageService,private msg:NzMessageService,public http:HttpClient,private api:ApiService) {
  }

  ngOnInit() {
    this.powers = JSON.parse(this._storage.get('powermap'));
    this.roles = this._storage.get('roles').split(';');
    this.menu.getMenu().then((result: any) => {
      this.navs = result.data;
    });
    this.getMessage();
    window.setTimeout(()=>this.getMessage,1000 * 60 * 5);
  }

  getSettingNav() {
    return this.settings.nav;
  }

  toggleCollapsed() {
    this.settings.setLayout('isCollapsed', !this.settings.layout.isCollapsed);
  }

  setNav(str) {
    this.settings.setnav(str);
  }

  signOut() {
    this._storage.clear();
    this.router.navigate(['login']);
  }
  showByRouter(routeurl){
    let url = routeurl.slice(1);
    let ifShow = false;
    if(!!this.powers)
      for(let i of this.powers[url])
        for(let j of this.roles)
          if(i==j)
            ifShow = true;
    return this.powers[url].indexOf(-1)!=-1 || ifShow
  }

  menuShowIfSubmenu(menu){
    let ifShow = false;
    if(!!menu.data)
      for(let item of menu.data){
        if(item.submenu){
          for(let submenu of item.submenu){
            if(this.showByRouter(submenu.route) && submenu.route!="/dashboard")
              return true;
          }
        }
        else{
          if(this.showByRouter(item.route) && item.route!="/dashboard")
            return true;
        }
      }
    if(!!menu.submenu){
      for(let item of menu.submenu)
        if(this.showByRouter(item.route))
          ifShow = true;
    }
    return ifShow;
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
  route:string;


  messagelist = [];
  unreadmessagelist = []
  getMessage(){
    this.http.post(this.api.BASEURL+"/read",{username:this._storage.get("username")}).subscribe(res=>{
      if(res['result'] == "success"){
        this.messagelist = res['data'];
        this.unreadmessagelist = res['data'].filter(item=>item.isread == 0);
      }
    })
    window.setTimeout(()=>this.getMessage,1000 * 60 * 5);
  }

  setRead(id){
    this.http.post(this.api.BASEURL+"/isread",{id:id}).subscribe(res=>{
      if(res['result'] == "success"){
        this.getMessage();
      }
    })
  }

  deleteMessage(id){
    this.http.post(this.api.BASEURL+"/deletemessage",{id:id}).subscribe(res=>{
      if(res['result'] == "success"){
        this.getMessage();
      }
    })
  }
  onlyUnRead = false; //只显示未读

}
