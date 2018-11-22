import {Component} from '@angular/core';
import {SettingsService} from '../../core/services/settings.service';
import {MenuService} from '../../core/services/menu.service';

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

  constructor(public settings: SettingsService, private menuService: MenuService) {
    this.theme = this.settings.layout.isDark;
    this.menuService.getNavs().then((result: any) => {
      this.navs = result.data;
    });
    this.menuService.getMenu().then((result: any) => {
      this.menulist = result.data;
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
