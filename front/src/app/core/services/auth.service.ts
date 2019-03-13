import {Injectable} from '@angular/core';
import {CanActivate, CanLoad, ActivatedRouteSnapshot, RouterStateSnapshot, Route, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {SessionStorageService} from '../storage/storage.module';
import {ApiService} from "../api/api.service";


@Injectable()
export class CanAuthProvide implements CanActivate {
  powers = {};
  roles = [];
  except = ['/login','/404','/'];

  constructor(private router: Router, private _storage: SessionStorageService,private api:ApiService,private http:HttpClient) {
    this.powers = JSON.parse(this._storage.get('powermap'));
    this.roles = this._storage.get('roles').split(';');
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    return this.check();
  }

  canLoad(route: Route): boolean | Observable<boolean> | Promise<boolean> {
    return this.check();
  }

  check(): boolean {
    if(!this.powers)
      this.http.get(this.api.BASEURL+"/getroutepower").subscribe(res=>{
        if(res['result'] == "success"){
          this.powers = JSON.parse(res['data']);
          this._storage.set('powermap',res['data'])
        }
      })
    const auth = this._storage.get('username');
    if(!auth){
      this.router.navigate(['/login']);
      return false;
    }else{
      console.log(this.powers);
      console.log(this.router.url.slice(1));
      console.log(this.powers[this.router.url.slice(1)])
      if(this.except.indexOf(this.router.url)!=-1 || this.powers[this.router.url.slice(1)].indexOf(0)!=-1)
        return true;
      else {
        let permission = false;
        for(let i of this.roles)
          for(let j of this.powers[this.router.url.slice(1)])
            if(i == j)
              permission = true;
        if(permission)
          return true;
        else
          this.router.navigate(['/404']);
      }
    }
    return false;
  }
}
