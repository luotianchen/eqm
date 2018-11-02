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
  constructor(public settings: SettingsService, public menu: MenuService, private router: Router, public _storage: SessionStorageService) {
    this.menu.getNavs().then((result: any) => {
      this.navs = result.data;
      this.settings.setnav(this.navs[0].name.toString());
    });
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

}
