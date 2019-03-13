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
  dataSet = [];
  power = null;
  rolemaps = {};
  roles = []
  mapOfExpandedData = {};
  //0为任何人，1为超级管理员！！
  constructor(public fb: FormBuilder, public message: NzMessageService,
              public accessPermissionService: AccessPermissionService, public router: Router, public _storage: SessionStorageService, public   menuService: MenuService) {
    this.accessPermissionService.getroutepower().subscribe(res => {
      if(res['result'] == "success"){
        this.power = JSON.parse(res['data']);
        this.menuService.getMenu().then((res: any) => {
          let key = 1;
          let menus = res['data'];
          let menuitem: { icon: string, name: string, route: string, submenu: any };
          for (let module of menus) {
            let it = [];
            for (menuitem of module.data) {
              if (!menuitem.submenu)
                it = [...it,{name: menuitem.name,icon:menuitem.icon, key: key++}];
              else {
                let submenus = [];
                for (let submenu of menuitem.submenu) {
                  submenus = [...submenus,{name: submenu['name'],icon:submenu['icon'], key: key++}];
                }
                it = [...it,{name: menuitem.name,icon:menuitem.icon, children: submenus, key: key++}];
              }
            }
            this.dataSet = [...this.dataSet, {name: module.name, children: it, key: key++}];
          }
          this.dataSet.forEach(item => {
            this.mapOfExpandedData[item.key] = this.convertTreeToList(item);
          });
        })
      }
    })

    this.accessPermissionService.getrole().subscribe((res)=>{
      if(res['result'] == "success"){
        this.rolemaps = []
        this.roles = res['data'];
        this.roles.push({
          "role": 0,
          "rolename": "所有人",
          "department": 0
        });
        for(let item of res['data'])
          this.rolemaps[item['role']] = item['rolename'];
        this.rolemaps[0] = "所有人";
        console.log(this.rolemaps);
      }
    })
  }

  ngOnInit(): void {
  }

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
  getLevelPower(menu){
    let res = [];
    if(!!menu.children)
      for(let item of menu.children){
        let cache =[];
        if(item.children) cache = this.getLevelPower(item);
        else cache = this.power[item.name];
        if(!!cache)
          for(let c of cache){
            if(res.indexOf(c)==-1)
              res.push(c);
          }
      }
    return res;
  }
  check(name){
    if(this.power[name].length == 0 && this.power[name].indexOf(0)==-1){
      this.power[name] = [0];
    }else if(this.power[name].indexOf(1) == -1 && this.power[name].indexOf(0) == -1){
      this.power[name].push(1);
    }
  }
  submitForm(){
    console.log(JSON.stringify(this.power));
  }
}
