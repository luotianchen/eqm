import {Component} from '@angular/core';
import {ProfileService} from "./profile.service";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {UploadFile,NzMessageService} from "ng-zorro-antd";
import {UploadFilter} from "ng-zorro-antd/upload";
@Component({
  selector: 'app-profile',
  templateUrl: 'profile.component.html',
  styleUrls: ['./profile.component.less'],
  providers: [ProfileService]
})
export class ProfileComponent {
  private department = new Set();
  private edit = false;
  private imgURL = null;
  private editimg = false;
  private name = this._storage.get("name");
  private email = this._storage.get("email");

  constructor(private profileService: ProfileService, private _storage: SessionStorageService, private msg: NzMessageService) {
  }

  ngOnInit(): void {
    this.profileService.getDepartment(this._storage.get("role")).subscribe((res) => {
      if (res['result'] == "success")
        this.department.add(res['departmentname']);
    });
    if(this._storage.get("role2")!='null')
      this.profileService.getDepartment(this._storage.get("role2")).subscribe((res) => {
        if (res['result'] == "success")
          this.department.add(res['departmentname']);

      });
    if(this._storage.get("role3")!='null')
      this.profileService.getDepartment(this._storage.get("role3")).subscribe((res) => {
        if (res['result'] == "success")
          this.department.add(res['departmentname']);

      });
    if(this._storage.get("role4")!='null')
      this.profileService.getDepartment(this._storage.get("role4")).subscribe((res) => {
        if (res['result'] == "success")
          this.department.add(res['departmentname']);

      });
    if(this._storage.get("role5")!='null')
      this.profileService.getDepartment(this._storage.get("role5")).subscribe((res) => {
        if (res['result'] == "success")
          this.department.add(res['departmentname']);

      });
    console.log(this.department)
    this.profileService.getSignImage(this._storage.get("username")).subscribe((res) => {
      if (res['result'] == "success") {
        this.imgURL = res['url'];
      } else {
        this.imgURL = null;
      }
    })
  }

  editName(){
    if(this.name!=null && this.name!=""){
      this.profileService.editName(this._storage.get("username"),this.name).subscribe((res)=>{
        if(res["result"]=="success"){
          this.msg.success("修改成功！");
          this._storage.set("name",this.name);
          this.edit = false;
        }
      })
    }
  }


  editEmail(){
    if(this.name!=null && this.name!=""){
      this.profileService.editEmail(this._storage.get("username"),this.email).subscribe((res)=>{
        if(res["result"]=="success"){
          this.msg.success("修改成功！");
          this._storage.set("email",this.email);
          this.edit = false;
        }
      })
    }
  }


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
    formData.append('username', this._storage.get("username"));
    this.profileService.uploadImg(formData).subscribe(
        (event: {}) => {
          this.uploading = false;
          this.msg.success('上传成功！');
          this.editimg = false;
          this.edit = false;
          this.avatarUrl = null;
          this.fileList = [];
          this.profileService.getSignImage(this._storage.get("username")).subscribe((res) => {
            if (res['result'] == "success") {
              this.imgURL = res['url'];
            } else {
              this.imgURL = null;
            }
          })
        },
        err => {
          this.uploading = false;
          this.msg.error('上传失败，请重新上传！');
          this.editimg = false;
          this.edit = false;
          this.avatarUrl = null;
          this.fileList = [];
        }
      );
  }

  private getBase64(img: File, callback: (img: {}) => void): void {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
  }
}