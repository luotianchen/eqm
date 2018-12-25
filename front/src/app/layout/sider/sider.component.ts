import {Component} from '@angular/core';
import {SettingsService} from '../../core/services/settings.service';
import {MenuService} from '../../core/services/menu.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sider',
  templateUrl: 'sider.component.html',
  styleUrls: ['./sider.component.less']
})

export class SiderComponent {
  theme = true;
  navs: any;
  menulist: any;
  menuOpenMap = {};

  typeofNav() {
    return typeof this.settings.nav === 'undefined';
  }

  getSettingNav() {
    return this.settings.nav;
  }

  constructor(public settings: SettingsService, private menuService: MenuService, private router: Router) {
  }

  ngOnInit() {
    this.theme = this.settings.layout.isDark;
    this.menuService.getNavs().then((result: any) => {
      this.navs = result.data;
    });
    this.menuService.getMenu().then((result: any) => {
      this.menulist = result.data;
      let flag: boolean = true;
      for (let sider of this.menulist) {
        let name = sider.name;
        for (let item of sider.data) {
          item.highlight = item.route == this.router.url;
          if (item.route == this.router.url) {
            flag = false;
          }
          if (item.submenu != null) {
            for (let sub of item.submenu) {
              sub.highlight = sub.route == this.router.url;
              if(sub.route == this.router.url){
                this.menuOpenMap[item.name] = true;
                this.settings.setnav(name.toString());
              }
            }
          }
        }
      }

      if (flag) {
        for (let sider of this.menulist) {
          sider.data[0].highlight = true;
        }
      }
    });
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
