import {Injectable} from '@angular/core';
import {NHttpClinet} from '../utils/http.client';
import {SettingsService} from 'src/app/core/services/settings.service';

@Injectable()
export class MenuService {
  constructor(private http: NHttpClinet, private setting: SettingsService) {

  }

  getMenu = () => new Promise((resolve, reject) => {
      this.http.get('/assets/api/menu.json')
        .subscribe(result => {
          this.setting.setMenuStatus(true);
          resolve(result);
        })
    });
  getNavs = () => this.getMenu();
}
