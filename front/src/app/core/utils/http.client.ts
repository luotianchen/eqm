import {catchError, tap} from 'rxjs/operators';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {environment} from '../../../environments/environment';
import {SessionStorageService} from 'src/app/core/storage/storage.service';

/**
 * 封装HttpClient，主要解决：
 * + 优化HttpClient在参数上便利性
 * + 统一实现 loading
 * + 统一处理时间格式问题
 */
@Injectable()
export class NHttpClinet {
  constructor(private http: HttpClient, private session: SessionStorageService) {
  }

  private _loading = false;
  /** 是否正在加载中 */
  get loading(): boolean {
    return this._loading;
  }

  /**
   * 处理参数
   */
  parseParams(params: any): HttpParams {
    let ret = new HttpParams();
    if (params) {
      // tslint:disable-next-line:forin
      for (const key in params) {
        let _data = params[key];
        // 将时间转化为：时间戳 (秒)
        if (moment.isDate(_data)) {
          _data = moment(_data).valueOf();
        }
        if (_data) {
          ret = ret.set(key, _data);
        }
      }
    }
    return ret;
  }

  /**
   * 处理请求头
   */
  parseHeaders(): HttpHeaders {
    const token = this.session.get('token');
    let res = new HttpHeaders();
    if (token) {
      res = res.set('token', token);
    }
    return res;
  }

  private begin() {
    this._loading = true;
  }

  private end() {
    this._loading = false;
  }

  /** 服务端URL地址 */
  static get SERVER_URL(): string {
    return environment.SERVER_URL;
  }

  /**
   * GET请求
   *
   */
  get(url: string, params?: any): Observable<any> {
    this.begin();
    return this.http
      .get(url, {
        params: this.parseParams(params)
      })
      .pipe(
        tap(() => this.end()),
        catchError((res) => {
          this.end();
          return res;
        }));
  }


  /**
   * POST请求
   *
   */
  post(url: string, body?: any, params?: any): Observable<any> {
    this.begin();
    return this.http
      .post(url, body || null, {
        headers: this.parseHeaders(),
        params: this.parseParams(params)
      }).pipe(
      tap(() => this.end()),
      catchError((res) => {
        this.end();
        return res;
      }));
  }

  /**
   * PUT请求
   */
  put(url: string, params?: any) {
    this.begin();
    return this.http
      .put(url, {
        headers: this.parseHeaders(),
        params: this.parseParams(params)
      }).pipe(
      tap(() => this.end()),
      catchError((res) => {
        this.end();
        return res;
      }));
  }

  /**
   * DELETE请求
   *
   */
  delete(url: string, params?: any): Observable<any> {
    this.begin();
    return this.http.delete(url, {
      headers: this.parseHeaders(),
      params: this.parseParams(params)
    }).pipe(
      tap(() => this.end()),
      catchError((res) => {
        this.end();
        return res;
      }));
  }
}
