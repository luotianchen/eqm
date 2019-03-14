import {Component} from '@angular/core';
import {SettingsService} from '../../core/services/settings.service';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {SessionStorageService} from "../../core/storage/storage.service";
import {filter} from "rxjs/internal/operators";
@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent {

  powers = {};
  roles = [];
  except = ['/login','/404','/'];

  constructor(public settings: SettingsService,private router: Router,private activatedRoute: ActivatedRoute,private _storage:SessionStorageService) {
    this.powers = JSON.parse(this._storage.get('powermap'));
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event:any) => {
      if(this._storage.get('username')){
        this.roles = this._storage.get('roles').split(';');
        //监听router改变结束事件
        // console.log('NavigationEnd:', event);
        if(this.except.indexOf(event.url)==-1 && this.powers[event.url.slice(1)].indexOf(-1)==-1){
          let permission = true;
          for(let i of this.powers[event.url.slice(1)])
            for(let j of this.roles)
              if(i==j)
                permission = false;
          if(permission)
            this.router.navigate(['404']);
        }
      }
    })
  }

}
