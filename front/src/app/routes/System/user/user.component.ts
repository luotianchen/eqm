import {Component} from '@angular/core';
import {SessionStorageService} from "../../../core/storage/storage.service";
import {NzMessageService} from "ng-zorro-antd";
import {Router} from "@angular/router";
import {UserService} from "./user.service";
import {UploadFile, UploadFilter} from "ng-zorro-antd/upload";
@Component({
  selector: 'app-user',
  templateUrl: 'user.component.html',
  styleUrls: ['./user.component.less'],
  providers: [UserService]
})
export class UserComponent {
  dataSet = [];
  dataSetDisplay = [];
  roles = null;
  imgURL = null;
  searchUname = '';
  searchname = '';
  isVisible = {
    name:false,
    sign:false,
    showsign:false,
    role:false,
    add:false,
    email:false,
    note:false
  };
  name = null;
  name_username = null;
  email = null;
  email_username = null;
  note = null;
  note_username = null;
  username = null;
  roleOptions = [];
  rolemodel = [];
  role_username = null;
  img_username = null;
  public editimg = false;

  mode = 0;//0为不显示，1为超级管理员模式，2为焊工、铆工模式

  ngOnInit(): void {
  }

  showModal(type,arg): void {
    this.isVisible[type] = true;
    if(type == "name"){
      this.name = arg.name;
      this.name_username = arg.username;
    }
    if(type == "email"){
      this.email = arg.email;
      this.email_username = arg.username;
    }
    if(type == "note"){
      this.note = arg.note;
      this.note_username = arg.username;
    }
    if(type == "sign"){
      this.img_username = arg;
    }
    if(type == "role"){
      this.rolemodel = [];
      if(arg.role!=0)
        this.rolemodel.push(arg.role);
      if(arg.role2!=0)
        this.rolemodel.push(arg.role2);
      if(arg.role3!=0)
        this.rolemodel.push(arg.role3);
      if(arg.role4!=0)
        this.rolemodel.push(arg.role4);
      if(arg.role5!=0)
        this.rolemodel.push(arg.role5);
      this.role_username = arg.username;
    }
    if(type=='showsign'){
      this.userService.getSignImage(arg).subscribe((res)=>{
        if(res["result"]=="success"){
          this.imgURL = res["url"];
        }
      })
    }
    if(type == "add"){
      this.name = null;
      this.username = null;
      this.rolemodel = [];
    }
  }

  handleOk(type): void {
    if(type == "name"){
      if(this.name==null||this.name==""){
        this.msg.error("姓名不能为空！");
      }else{
        this.userService.editName(this.name_username,this.name).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("修改成功！");
            this.isVisible[type] = false;
            this.userService.getUsers().subscribe((res)=>{
              if(res["result"]=="success"){
                if(this.mode == 2){
                  this.dataSet = [];
                  for(let data of res["data"]){
                    if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                      this.dataSet.push(data);
                    }
                  }
                }else{
                  this.dataSet = res["data"];
                }
                this.dataSetDisplay = this.dataSet;
              }
            });
          }
        })
      }
    }
    if(type == "email"){
      if(this.email==null||this.email==""){
        this.msg.error("邮箱不能为空！");
      }else{
        this.userService.editEmail(this.email_username,this.email).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("修改成功！");
            this.isVisible[type] = false;
            this.userService.getUsers().subscribe((res)=>{
              if(res["result"]=="success"){
                if(this.mode == 2){
                  this.dataSet = [];
                  for(let data of res["data"]){
                    if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                      this.dataSet.push(data);
                    }
                  }
                }else{
                  this.dataSet = res["data"];
                }
                this.dataSetDisplay = this.dataSet;
              }
            });
          }
        })
      }
    }
    if(type == "note"){
      if(this.note==null||this.note==""){
        this.msg.error("备注不能为空！");
      }else{
        this.userService.editNote(this.note_username,this.note).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("修改成功！");
            this.isVisible[type] = false;
            this.userService.getUsers().subscribe((res)=>{
              if(res["result"]=="success"){
                if(this.mode == 2){
                  this.dataSet = [];
                  for(let data of res["data"]){
                    if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                      this.dataSet.push(data);
                    }
                  }
                }else{
                  this.dataSet = res["data"];
                }
                this.dataSetDisplay = this.dataSet;
              }
            });
          }
        })
      }
    }
    if(type == "role"){
      if (this.rolemodel.length==0){
        this.msg.error("请至少为该用户分配一个角色！");
      }else{
        this.userService.setRoles(this.role_username,this.rolemodel[0]==null?0:this.rolemodel[0],this.rolemodel[1]==null?0:this.rolemodel[1],this.rolemodel[2]==null?0:this.rolemodel[2],this.rolemodel[3]==null?0:this.rolemodel[3],this.rolemodel[4]==null?0:this.rolemodel[4]).subscribe((res)=>{
          if(res["result"] == "success"){
            this.msg.success("修改用户角色成功！");
            this.isVisible[type] = false;
            this.userService.getUsers().subscribe((res)=>{
              if(res["result"]=="success"){
                if(this.mode == 2){
                  this.dataSet = [];
                  for(let data of res["data"]){
                    if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                      this.dataSet.push(data);
                    }
                  }
                }else{
                  this.dataSet = res["data"];
                }
                this.dataSetDisplay = this.dataSet;
              }
            });
          }else{
            this.msg.error("修改失败！");
          }
        })
      }
    }
    if(type == "add"){
      if(this.username==null||this.username==""){
        this.msg.error("用户名不能为空！");
        return;
      }
      if(!/^[a-z][a-z0-9_]*$/.test(this.username)){
        this.msg.error('用户名必须以小写英文字母开头，且只能包含英文字母、数字、下划线')
        return;
      }
      if(this.name==null||this.name==""){
        this.msg.error("姓名不能为空！");
        return;
      }
      if (this.rolemodel.length==0){
        this.msg.error("请至少为该用户分配一个角色！");
        return;
      }
      this.userService.addUser(this.username,this.name,this.rolemodel[0]==null?0:this.rolemodel[0],this.rolemodel[1]==null?0:this.rolemodel[1],this.rolemodel[2]==null?0:this.rolemodel[2],this.rolemodel[3]==null?0:this.rolemodel[3],this.rolemodel[4]==null?0:this.rolemodel[4]).subscribe((res)=>{
        if(res["result"]=="success"){
          this.msg.success("新增用户成功！");
          this.msg.success("用户"+this.username+"的初始密码为123456，请尽快修改密码！");
          this.isVisible[type] = false;
          this.userService.getUsers().subscribe((res)=>{
            if(res["result"]=="success"){
              if(this.mode == 2){
                this.dataSet = [];
                for(let data of res["data"]){
                  if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                    this.dataSet.push(data);
                  }
                }
              }else{
                this.dataSet = res["data"];
              }
              this.dataSetDisplay = this.dataSet;
            }
          });
        }
      })
    }
    if(type=="showsign")
      this.isVisible[type] = false;
  }

  handleCancel(type): void {
    this.isVisible[type] = false;
    if(type=="sign"){
      this.uploading = false;
      this.editimg = false;
      this.avatarUrl = null;
      this.fileList = [];
    }
  }
  constructor(public _storage: SessionStorageService,public userService: UserService, public msg: NzMessageService, public router: Router) {
    let roles = this._storage.get("roles").split(';');
    if(roles.indexOf("1")!=-1){
      this.mode = 1
    } else {
      this.mode = 2;
    }
    this.userService.getRoles().subscribe((res)=>{
      if(res["result"]=="success"){
        this.roles = [];
        this.roleOptions = res["data"];
        for(let item of res["data"]){
          this.roles[item.role] = {"rolename":item.rolename,"department":item.department};
        }
      }
    });
    this.userService.getUsers().subscribe((res)=>{
      if(res["result"]=="success"){
        if(this.mode == 2){
          this.dataSet = [];
          for(let data of res["data"]){
            if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
              this.dataSet.push(data);
            }
          }
        }else{
          this.dataSet = res["data"];
        }
        this.dataSetDisplay = this.dataSet;
      }
    });
  }


  //上传
  fileList = [];
  uploading = false;
  avatarUrl: string;

  beforeUpload = (file: any): boolean => {
    this.fileList.push(file);
    let info:File = file;
    this.getBase64(info, (img: string) => {
      this.avatarUrl = img;
    });
    return false;
  };

  filters: UploadFilter[] = [
    {
      name: 'type',
      fn  : (fileList: UploadFile[]) => {
        const filterFiles = fileList.filter(w => ~['image/png', 'image/jpeg'].indexOf(w.type));
        if (filterFiles.length !== fileList.length) {
          this.msg.error(`包含文件格式不正确，只支持 jpg、png 格式`);
          return filterFiles;
        }
        const isLt2M = fileList[0].size / 1024 / 1024 < 5;
        if (!isLt2M) {
          this.msg.error('可上传图片文件最大为5MB！');
        }
        return fileList;
      }
    }
  ];

  handleUpload(): void {
    this.uploading = true;
    const formData = new FormData();
    formData.append('signature', this.fileList[0]);
    formData.append('username', this.img_username);
    this.userService.uploadImg(formData).subscribe(
      (event: {}) => {
        this.uploading = false;
        this.msg.success('上传成功！');
        this.editimg = false;
        this.avatarUrl = null;
        this.fileList = [];
        this.userService.getUsers().subscribe((res)=>{
          if(res["result"]=="success"){
            if(this.mode == 2){
              this.dataSet = [];
              for(let data of res["data"]){
                if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                  this.dataSet.push(data);
                }
              }
            }else{
              this.dataSet = res["data"];
            }
            this.dataSetDisplay = this.dataSet;
          }
        });
        this.isVisible.sign = false;
      },
      err => {
        this.uploading = false;
        this.msg.error('上传失败，请重新上传！');
        this.editimg = false;
        this.avatarUrl = null;
        this.fileList = [];
      }
    );
  }
  public getBase64(img: File, callback: (img: {}) => void): void {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
  }

  confirm(username): void {
    this.userService.resetPSW(username).subscribe((res)=>{
      if(res['result']=="success"){
        this.msg.success("重置用户"+username+"的密码为123456成功，请尽快修改密码！");
      }
    });
  }

  confirmDelete(username):void {
    this.userService.deleteUser(username).subscribe((res)=>{
      if(res['result']=="success"){
        this.msg.success("删除成功！");
        this.userService.getUsers().subscribe((res)=>{
          if(res["result"]=="success"){
            if(this.mode == 2){
              this.dataSet = [];
              for(let data of res["data"]){
                if(data.role==56 || data.role==55 || data.role2==56 || data.role2==55 ||data.role3==56 || data.role3==55 ||data.role4==56 || data.role4==55 ||data.role5==56 || data.role5==55){
                  this.dataSet.push(data);
                }
              }
            }else{
              this.dataSet = res["data"];
            }
            this.dataSetDisplay = this.dataSet;
          }
        });
      }else{
        this.msg.error("因系统中存在该用户的操作记录，本次操作失败！")
      }
    })
  }

  searchUsername(): void {
    const filterFunc = (item) => {
      return item.username.indexOf(this.searchUname) !== -1;
    };
    this.dataSetDisplay = this.dataSet.filter(item => filterFunc(item));
  }

  searchName(): void {
    const filterFunc = (item) => {
      return item.name.indexOf(this.searchname) !== -1;
    };
    this.dataSetDisplay = this.dataSet.filter(item => filterFunc(item));
  }

}
