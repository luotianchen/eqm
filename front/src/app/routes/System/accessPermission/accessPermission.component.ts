import {Component, OnInit} from '@angular/core';
import {FormGroup, Validators, FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';
import {AccessPermissionService} from "./accessPermission.service";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {MenuService} from "../../../core/services/menu.service";
import {TreeNodeInterface} from "../role/role.component";

@Component({
  selector: 'app-accessPermission',
  templateUrl: 'accessPermission.component.html',
  styleUrls: ['./accessPermission.component.less'],
  providers: [AccessPermissionService]
})

export class AccessPermissionComponent implements OnInit {
  constructor(public fb: FormBuilder, public message: NzMessageService,
              public accessPermissionService: AccessPermissionService, public router: Router, public _storage: SessionStorageService, public   menuService: MenuService) {
    this.menuService.getMenu().then((res: any) => {
      let menus = res.data;
      let key = 1;
      let menuitem: { icon: string, name: string, route: string, submenu: [] };
      for (let module of menus) {
        let it = [];
        for (menuitem of module.data) {
          if (!menuitem.submenu)
            it.push({name: menuitem.name,icon:menuitem.icon, key: key++});
          else {
            let submenus = [];
            for (let submenu of menuitem.submenu) {
              submenus.push({name: submenu['name'],icon:submenu['icon'], key: key++});
            }
            it.push({name: menuitem.name,icon:menuitem.icon, children: submenus, key: key++});
          }
        }
        this.dataSet = [...this.dataSet, {name: module.name, children: it, key: key++}];
      }
      this.dataSet.forEach(item => {
        this.mapOfExpandedData[item.key] = this.convertTreeToList(item);
      });
    })
  }

  dataSet = [];
  power = {};
  ngOnInit() {
    this.accessPermissionService.getroutepower().subscribe(res=>{
      if(res['result'] == "success"){
        this.power = JSON.parse(res['data']);
      }
    })
  }

  mapOfExpandedData = {};

  collapse(array: TreeNodeInterface[], data: TreeNodeInterface, $event: boolean): void {
    if ($event === false) {
      if (data.children) {
        data.children.forEach(d => {
          const target = array.find(a => a.key === d.key);
          target.expand = false;
          this.collapse(array, target, false);
        });
      } else {
        return;
      }
    }
  }

  convertTreeToList(root: object): TreeNodeInterface[] {
    const stack = [];
    const array = [];
    const hashMap = {};
    stack.push({...root, level: 0, expand: false});

    while (stack.length !== 0) {
      const node = stack.pop();
      this.visitNode(node, hashMap, array);
      if (node.children) {
        for (let i = node.children.length - 1; i >= 0; i--) {
          stack.push({...node.children[i], level: node.level + 1, expand: false, parent: node});
        }
      }
    }

    return array;
  }

  visitNode(node: TreeNodeInterface, hashMap: object, array: TreeNodeInterface[]): void {
    if (!hashMap[node.key]) {
      hashMap[node.key] = true;
      array.push(node);
    }
  }
  getLevel1Power(menu){
     let res = [];
     for(let item of menu.children){
       let powers = this.power[item.name];
       if(!!powers)
         for(let p of powers)
           if(res.indexOf(p) == -1)
             res.push(p)
     }
     return res;
  }
  getLevel0Power(menu){
    let res = [];
    for(let item of menu.children){
      let cache =[];
      if(item.children) cache = this.getLevel1Power(item);
      else cache = this.power[item.name];
      if(!!cache)
        for(let c of cache){
          if(res.indexOf(c)==-1)
            res.push(c);
        }
    }
    return res;
  }
}
