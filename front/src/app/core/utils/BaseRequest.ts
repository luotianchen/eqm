import {NHttpClinet} from 'src/app/core/utils/http.client';
import {ResponseModel} from 'src/app/core/utils/ResponseModel';

/**
 * BaseService contains public crud
 */
export class BaseService {
  module: string;
  http: NHttpClinet;

  constructor(module: string, http: NHttpClinet) {
    this.module = module;
    this.http = http;
  }

  /**
   * get all the record
   */
  queryAll() {
    return new Promise((resolve, reject) => {
      this.http.get(`${this.http.SERVER_URL}${this.module}/all`).subscribe((response: ResponseModel) => {
        if (response.result === '00000000') {
          resolve(response.data);
        } else {
          reject(response.result);
        }
      });
    });
  }

  /**
   * get record by id
   */
  queryById(id: string) {
    return new Promise((resolve, reject) => {
      this.http.get(`${this.http.SERVER_URL}${this.module}/${id}`).subscribe((response: ResponseModel) => {
        if (response.result === '00000000') {
          resolve(response.data);
        } else {
          reject(response.result);
        }
      });
    });
  }

  /**
   * create an record
   */
  create(params: Object) {
    return new Promise((resolve, reject) => {
      this.http.post(`${this.http.SERVER_URL}${this.module}`, params).subscribe((response: ResponseModel) => {
        if (response.result === '00000000') {
          resolve(response.data);
        } else {
          reject(response.result);
        }
      });
    });
  }

  /**
   * update an record
   */
  update(id: string, params: any) {
    return new Promise((resolve, reject) => {
      this.http.put(`${this.http.SERVER_URL}${this.module}/${id}`, params).subscribe((response: ResponseModel) => {
        if (response.result === '00000000') {
          resolve(response.data);
        } else {
          reject(response.result);
        }
      });
    });
  }

  /**
   * get paging record
   */
  queryList(pageIndex: number, pageSize: number, searchParmas?: {}) {
    return new Promise((resolve, reject) => {
      this.http.get(`${this.http.SERVER_URL}${this.module}?pageNum=${pageIndex}&pageSize=${pageSize}`, searchParmas).subscribe((response: ResponseModel) => {
        if (response.result === '00000000') {
          resolve(response.data);
        } else {
          reject(response.result);
        }
      });
    });
  }

  /**
   * remove an record
   */
  remove(id: string) {
    return new Promise((resolve, reject) => {
      this.http.delete(`${this.http.SERVER_URL}${this.module}/` + id).subscribe((response: ResponseModel) => {
        if (response.result === '00000000') {
          resolve(response.data);
        } else {
          reject(response.result);
        }
      });
    });
  }
}
