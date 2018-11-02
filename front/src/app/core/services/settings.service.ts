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
  private __nav: string;

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
    return this.__nav;
  }

  setnav(value) {
    this.__nav = value;
  }
}
