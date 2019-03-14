import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {SettingsService} from '../../core/services/settings.service';
import {MenuService} from '../../core/services/menu.service';
import {SessionStorageService} from 'src/app/core/storage/storage.module';

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['./header.component.less'],
})

export class HeaderComponent {
  navs = null;
  searchstatus = false;
  powers = {};
  roles = [];
  constructor(public settings: SettingsService, public menu: MenuService, public router: Router, public _storage: SessionStorageService) {
  }
  ngOnInit(){
    this.powers = JSON.parse(this._storage.get('powermap'));
    this.roles = this._storage.get('roles').split(';');
    this.menu.getMenu().then((result: any) => {
      this.navs = result.data;
    });
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

}
