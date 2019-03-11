import {Component} from '@angular/core';
import {SettingsService} from '../../core/services/settings.service';
import {MenuService} from '../../core/services/menu.service';
import {Router} from '@angular/router';
import {isUndefined} from "util";

@Component({
  selector: 'app-sider',
  templateUrl: 'sider.component.html',
  styleUrls: ['./sider.component.less']
})

export class SiderComponent {
  theme = this.settings.layout.isDark;
  menulist: any;
  menuOpenMap = {};
  typeofNav() {
    return typeof this.settings.nav === 'undefined';
  }

  getSettingNav() {
    return this.settings.nav;
  }

  constructor(public settings: SettingsService, private menuService: MenuService, private router: Router) {
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
  }

  ngOnInit() {
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
