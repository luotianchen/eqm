import {Injectable} from '@angular/core';
import {CanActivate, CanLoad, ActivatedRouteSnapshot, RouterStateSnapshot, Route, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {SessionStorageService} from '../storage/storage.module';
import {ApiService} from "../api/api.service";


@Injectable()
export class CanAuthProvide implements CanActivate {
  powers = [];
  constructor(private router: Router, private _storage: SessionStorageService,private api:ApiService,private http:HttpClient) {
    this.http.get("https://www.easy-mock.com/mock/5bd28b54c16e907322bb019e/eqm/getroutepower").subscribe(res=>{
      if(res['result'] == "success")
        this.powers = res['data']
    })
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    return this.check();
  }

  canLoad(route: Route): boolean | Observable<boolean> | Promise<boolean> {
    return this.check();
  }

  check(): boolean {
    const auth = this._storage.get('username');
    if (auth) {
      return true;
    }
    const havepower = this._storage.get('roles').split(';');
    if(this.powers[this.router.url.slice(1)].indexOf(0)!=-1 || this.powers[this.router.url.slice(1)].some((role)=>havepower.indexOf(role)!=-1)){
    }else{
      this.router.navigate(['/404']);
    }
    this.router.navigate(['/login']);
    return false;
  }
}
