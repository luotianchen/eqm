import {Component, OnInit} from '@angular/core';
import {RoleService} from "./role.service";
import {NzMessageService} from "ng-zorro-antd";

export interface TreeNodeInterface {
  key:number,
  role:string,
  rolename:string,
  department:string,
  departmentid:string,
  expand: boolean;
  address: string;
  children?: TreeNodeInterface[];
}

@Component({
  selector: 'app-role',
  templateUrl: 'role.component.html',
  styleUrls: ['./role.component.less'],
  providers: [RoleService]
})

export class RoleComponent implements OnInit {
  data = [];
  roles = [];
  users = [];
  roles2 = null;
  username = null;
  name = null;
  email = null;
  role=null;
  role2=null;
  role3=null;
  role4=null;
  role5=null;
  i = 1;
  j = 1;
  k = 1;
  departmentId = null;
  roleId = null;
  isVisible = {
    changeDepartmentName:false,
    addRole:false,
    showUser:false,
    moveRole:false,
    addDepartment:false,
    changeRoleName:false
  };
  datamodel = {
    departmentname:null,
    roleName:null,
    department:null
  };
  constructor(public roleService: RoleService, public msg: NzMessageService) {
  }
  ngOnInit(): void {
    this.getData();
  }

  getData(){
    this.roleService.getRoles().subscribe((res)=>{
      if(res['result']=="success"){
        this.roles = res['data'];
        this.roles2 = [];
        for(let item of res["data"]){
          this.roles2[item.role] = item.rolename;
        }
        this.roleService.getDepartments().subscribe((res)=>{
          if(res['result']=="success"){
            this.data = res["data"].filter(item => item.department!=0);
            this.roleService.getUsers().subscribe((res)=>{
              if(res['result']=="success"){
                this.users = res['data'];
                for(let item of this.data){
                  item.key = this.i++;
                  for(let roleitem of this.roles){
                    roleitem.key = this.i*10+(this.j++);
                    if(item.department == roleitem.department){
                      if(!item.children)
                        item.children = [];
                      item.children.push(roleitem);
                    }
                    for(let useritem of this.users){
                      let flag = false;
                      if(!roleitem.children)
                        roleitem.children = [];
                      for(let item of roleitem.children){
                        if(item.username == useritem.username){
                          flag = true;
                          break;
                        }
                      }
                      if(flag)
                        continue;
                      if(useritem.role==roleitem.role){
                        let userq  = {};
                        for(let i in useritem)
                          userq[i] = useritem[i];
                        userq['key'] = this.i*100+this.j*10+this.k++;
                        roleitem.children.push(userq);
                      }
                      if(useritem.role2==roleitem.role){
                        let userq  = {};
                        for(let i in useritem)
                          userq[i] = useritem[i];
                        roleitem.children.push(userq);
                      }
                      if(useritem.role3==roleitem.role){
                        let userq  = {};
                        for(let i in useritem)
                          userq[i] = useritem[i];
                        userq['key'] = this.i*100+this.j*10+this.k++;
                        roleitem.children.push(userq);
                      }
                      if(useritem.role4==roleitem.role){
                        let userq  = {};
                        for(let i in useritem)
                          userq[i] = useritem[i];
                        userq['key'] = this.i*100+this.j*10+this.k++;
                        roleitem.children.push(userq);
                      }
                      if(useritem.role5==roleitem.role){
                        let userq  = {};
                        for(let i in useritem)
                          userq[i] = useritem[i];
                        userq['key'] = this.i*100+this.j*10+this.k++;
                        roleitem.children.push(userq);
                      }
                    }
                    this.k = 1;
                  }
                  this.j = 1;
                }
                this.data.forEach(item => {
                  this.expandDataCache[ item.key ] = this.convertTreeToList(item);
                });
              }
            })
          }
        });
      }
    });
  }
  expandDataCache = {};

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
    stack.push({ ...root, level: 0, expand: false });

    while (stack.length !== 0) {
      const node = stack.pop();
      this.visitNode(node, hashMap, array);
      if (node.children) {
        for (let i = node.children.length - 1; i >= 0; i--) {
          stack.push({ ...node.children[ i ], level: node.level + 1, expand: false, parent: node });
        }
      }
    }

    return array;
  }

  visitNode(node: TreeNodeInterface, hashMap: object, array: TreeNodeInterface[]): void {
    if (!hashMap[ node.key ]) {
      hashMap[ node.key ] = true;
      array.push(node);
    }
  }

  showModal(type,args): void {
    this.isVisible[type] = true;
    if(type=="changeDepartmentName" || type=="addRole")
      this.departmentId = args;
    if(type=="changeRoleName" || "moveRole")
      this.roleId = args;
    if(type=="showUser"){
      this.username = args.username;
      this.name = args.name;
      this.email = args.email;
      this.role = this.roles2[args.role];
      this.role2 = this.roles2[args.role2];
      this.role3 = this.roles2[args.role3];
      this.role4 = this.roles2[args.role4];
      this.role5 = this.roles2[args.role5];
    }
  }

  handleOk(type): void {
    if(type=="changeDepartmentName"){
      if(this.datamodel.departmentname!=null&&this.datamodel.departmentname!=""){
        this.roleService.changeDepartmentName(this.departmentId,this.datamodel.departmentname).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("修改成功！");
            this.isVisible[type] = false;
            this.getData();
          }else{
            this.msg.error("修改失败，请稍后重试！")
          }
        })

      }else{
        this.msg.error("新部门名称不能为空！");
      }
    }
    if(type=="addRole"){
      if(this.datamodel.roleName==null||this.datamodel.roleName==""){
        this.msg.error("角色名不能为空！");
      }else{
        this.roleService.addRole(this.departmentId,this.datamodel.roleName).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("添加成功！");
            this.isVisible[type] = false;
            this.getData();
          }
        })
      }
    }
    if(type=="addDepartment"){
      if(this.datamodel.departmentname!=null&&this.datamodel.departmentname!=""){
        this.roleService.addDepartment(this.datamodel.departmentname).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("增加成功！");
            this.isVisible[type] = false;
            this.getData();
          }else{
            this.msg.error("增加失败，请稍后重试！")
          }
        })
      }else{
        this.msg.error("新部门名称不能为空！");
      }
    }
    if(type=="changeroleName"){
      if(this.datamodel.roleName==null||this.datamodel.roleName==""){
        this.msg.error("角色名不能为空！");
      }else{
        this.roleService.changeRoleName(this.roleId,this.datamodel.roleName).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("修改成功！");
            this.isVisible[type] = false;
            this.getData();
          }else{
            this.msg.error("修改失败，请稍后重试！")
          }
        })
      }
    }
    if(type=="moveRole"){
      if(this.datamodel.department == null || this.datamodel.department ==""){
        this.msg.error("请选择你要移至的部门！");
      }else{
        this.roleService.moveRole(this.roleId,this.datamodel.department).subscribe((res)=>{
          if(res['result']=="success"){
            this.msg.success("移动成功！");
            this.isVisible[type] = false;
            this.getData();
          }else{
            this.msg.error("移动失败，请稍后重试！")
          }
        })
      }
    }
    if(type=="showUser")
      this.isVisible[type] = false;
  }

  handleCancel(type): void {
    this.isVisible[type] = false;
    this.datamodel = {
      departmentname:null,
      roleName:null,
      department:null
    };
  }
  confirmDeleteDepartment(department){
    this.roleService.deleteDepartment(department).subscribe((res)=>{
      if(res["result"]=='success'){
        this.msg.success("删除成功！");
        this.getData();
      }else{
        this.msg.error("该部门下还有角色，删除失败！");
      }
    });
  }
  confirmDeleteRole(role){
    if(role == 55 || role == 56){
      this.msg.error("焊工和铆工不能被删除！");
      return;
    }
    if(role == 1){
      this.msg.error("检验员不能删除！");
      return;
    }
    this.roleService.deleteRole(role).subscribe((res)=>{
      if(res["result"]=='success'){
        this.msg.success("删除成功！");
        this.getData();
      }else{
        this.msg.error("该角色下还有用户，删除失败！");
      }
    });
  }
  showEditRoleTooltip(){
    this.msg.info("请勿修改材料代用相关部门名称！（设计责任工程师、材料责任工程师、焊接责任工程师、工艺责任工程师、检验与试验责任工程师、技术负责人）")
  }
}
