import {Component} from '@angular/core';
import {SettingsService} from '../../core/services/settings.service';
import { NavigationEnd, Router} from "@angular/router";
import {SessionStorageService} from "../../core/storage/storage.service";
import {filter} from "rxjs/internal/operators";
import {HttpClient} from "@angular/common/http";
import {ApiService} from "../../core/api/api.service";
import {MenuService} from "../../core/services/menu.service";
@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent {

  powers = {};
  roles = [];
  except = ['/login','/404','/','/profile/me','/profile/chanegePassword'];
  constructor(public settings: SettingsService,private router: Router,private _storage:SessionStorageService,private http:HttpClient,private api:ApiService) {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event:any) => {
      //监听router改变结束事件
      // console.log('NavigationEnd:', event);
      if(this._storage.get('username')){
        this.roles = this._storage.get('roles').split(';');
        if(!this._storage.get('powermap')) this.http.get(this.api.BASEURL+"/getroutepower").subscribe((res:string)=>this.navagitionEnd(res,event));
        else this.navagitionEnd(null,event)
      }
    })
  }
  navagitionEnd(powermap:string = null,event:any){
    if(powermap) this._storage.set('powermap',powermap);
    this.powers = JSON.parse(this._storage.get('powermap'));
    if(event.url == "/") this.router.navigate(['login']);
    else if(this.except.indexOf(event.url)==-1 && !!event.url.slice(1) && this.powers[event.url.slice(1)].indexOf(-1)==-1){
      let permission = true;
      for(let i of this.powers[event.url.slice(1)])
        for(let j of this.roles)
          if(i==j) permission = false;
      if(permission) this.router.navigate(['404']);
    }
  }


}
