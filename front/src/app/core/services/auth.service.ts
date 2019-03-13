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
    console.log(this.powers);
    console.log(this.roles);
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    return this.check();
  }

  canLoad(route: Route): boolean | Observable<boolean> | Promise<boolean> {
    return this.check();
  }

  check(): boolean {
    const auth = this._storage.get('username');
    const permited = (this.except.indexOf(this.router.url)!=-1) || this.powers[this.router.url.slice(1)].indexOf(0)!=-1 || this.powers[this.router.url.slice(1)].some((role)=>this.roles.indexOf(role)!=-1);
    if (auth && permited) {
        return true
    }else if(!auth){
      this.router.navigate(['/login']);
    }else{
      this.router.navigate(['/404']);
    }
    return false;
  }
}
