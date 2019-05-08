import {Injectable} from '@angular/core';
import {LocalStorageService} from '../storage/storage.service';

export interface Layout {
  isCollapsed: true;
  isDark: false;
}

@Injectable()
export class SettingsService {
  private _layout;
  private _loaded: boolean;
  private _nav: string;
  private _nav_cache: string = "材料管理";
  private _menulist: any;
  private _menuOpenMap = {};

  constructor(private _local: LocalStorageService) {
    const layout = this._local.get('layout');
    this._layout = layout ? JSON.parse(layout) : null;
  }

  get layout(): Layout {
    if (!this._layout) {
      this._layout = {
        isCollapsed: false,
        isDark: true
      };
    }
    return this._layout;
  }

  setLayout(name: string, value: any): boolean {
    if (typeof this.layout[name] !== 'undefined') {
      this._layout[name] = value;
      this._local.set('layout', JSON.stringify(this._layout));
      return true;
    }
    this.layout[name] = false;
    return false;
  }

  get menuStatus(): boolean {
    if (this._loaded === undefined) {
      this._loaded = false;
    }
    return this._loaded;
  }

  setMenuStatus(value: boolean) {
    this._loaded = value;
  }

  get nav():string {
    return this._nav;
  }

  setnav(value) {
    this._nav = value;
  }

  get nav_cache():string {
    return this._nav_cache;
  }

  setnav_cache(value) {
    this._nav_cache = value;
  }

  get menulist():any {
    return this._menulist;
  }

  setmenulist(value) {
    this._menulist = value;
  }

  get menuOpenMap():any {
    return this._menuOpenMap;
  }

  setmenuOpenMap(value){
    this._menuOpenMap = value;
  }

  oepnMenuOpenMapHandle(name,value){
    this._menuOpenMap[name] = value;
  }
}
