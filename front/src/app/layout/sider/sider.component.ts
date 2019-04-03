import {Component} from '@angular/core';
import {SettingsService} from '../../core/services/settings.service';
import {MenuService} from '../../core/services/menu.service';
import {Router} from '@angular/router';
import {SessionStorageService} from "../../core/storage/storage.service";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-sider',
  templateUrl: 'sider.component.html',
  styleUrls: ['./sider.component.less']
})

export class SiderComponent {
  theme = this.settings.layout.isDark;
  menulist: any;
  menuOpenMap = {};
  powers = {};
  roles = [];
  typeofNav() {
    return typeof this.settings.nav === 'undefined';
  }

  getSettingNav() {
    return this.settings.nav;
  }

  constructor(public settings: SettingsService, private menuService: MenuService, private router: Router, private _storage:SessionStorageService,private msg:NzMessageService) {
    this.updateMenu();
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
    if(menu.data)
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
    if(menu.submenu){
      for(let item of menu.submenu)
        if(this.showByRouter(item.route))
          ifShow = true;
    }
    return ifShow;
  }

  updateMenu(){
    setTimeout(() => {
      this.menuService.getMenu().then((result: any) => {
        this.menulist = result.data;
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
                  this.menuOpenMap[item.name] = true;
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

  ngOnInit() {
    if(this._storage.get('powermap')){
      this.powers = JSON.parse(this._storage.get('powermap'));
      this.roles = this._storage.get('roles').split(';');
    }else{
      this._storage.clear();
      this.msg.error("权限信息初始化失败！请重新登录")
      this.router.navigate(['/login']);
    }
  }

  openHandler(value: string): void {
    for (const key in this.menuOpenMap) {
      if (key !== value) {
        this.menuOpenMap[ key ] = false;
      }
    }
  }

  switch() {
    this.settings.setLayout('isDark', !this.settings.layout.isDark);
  }
}
