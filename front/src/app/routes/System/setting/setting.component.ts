import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {NzMessageService, NzModalRef, NzModalService} from 'ng-zorro-antd';
import {SettingService} from './setting.service';
import {UploadFile, UploadFilter} from "ng-zorro-antd/upload";

@Component({
  selector: 'app-setting',
  templateUrl: 'setting.component.html',
  styleUrls: ['./setting.component.less'],
  providers: [SettingService]
})

export class SettingComponent implements OnInit {
  validateForm: FormGroup;
  matlcodeValidateForm: FormGroup;
  public imgURL = null;
  public editimg = false;
  constructor(public fb: FormBuilder, public settingService: SettingService,public _storage: SessionStorageService,public msg:NzMessageService,public modalService: NzModalService) {
  }
  ngOnInit(): void {
    this.matlcodeValidateForm = this.fb.group({
      index:[null,[Validators.required,Validators.maxLength(1)]],
      plank:[null,[Validators.required,Validators.maxLength(1)]],
      pipe:[null,[Validators.required,Validators.maxLength(1)]],
      flange:[null,[Validators.required,Validators.maxLength(1)]],
      head:[null,[Validators.required,Validators.maxLength(1)]],
      welding:[null,[Validators.required,Validators.maxLength(1)]],
    });
    this.settingService.getMatlcodeRules().subscribe(res=>{
      if(res['result']=='success'){
        this.matlcodeValidateForm.controls['index'].setValue(res['index']);
        this.matlcodeValidateForm.controls['plank'].setValue(res['plank']);
        this.matlcodeValidateForm.controls['pipe'].setValue(res['pipe']);
        this.matlcodeValidateForm.controls['flange'].setValue(res['flange']);
        this.matlcodeValidateForm.controls['head'].setValue(res['head']);
        this.matlcodeValidateForm.controls['welding'].setValue(res['welding']);
      }
    })
    this.validateForm = this.fb.group({
      "deconame":[null,[Validators.required]],
      "edeconame":[null,[Validators.required]],
      "delicense":[null,[Validators.required]],
      "manulevel":[null,[Validators.required]],
      "time":[null,[Validators.required]],
      "orgcode":[null,[Validators.required]],
      "name":[null,[Validators.required]],
      "ename":[null,[Validators.required]],
      "uniformcode":[null,[Validators.required]],
      "manuno":[null,[Validators.required]]
    });
    this.settingService.getSettingInfo().subscribe((res)=>{
      if(res['result']=="success"){
        this.validateForm.controls['deconame'].setValue(res['deconame']);
        this.validateForm.controls['edeconame'].setValue(res['edeconame']);
        this.validateForm.controls['delicense'].setValue(res['delicense']);
        this.validateForm.controls['manulevel'].setValue(res['manulevel']);
        this.validateForm.controls['time'].setValue(res['time']);
        this.validateForm.controls['orgcode'].setValue(res['orgcode']);
        this.validateForm.controls['name'].setValue(res['name']);
        this.validateForm.controls['ename'].setValue(res['ename']);
        this.validateForm.controls['uniformcode'].setValue(res['uniformcode']);
        this.validateForm.controls['manuno'].setValue(res['manuno']);
      }
    })
  }

  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.settingService.putSettingInfo(this.validateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '设置成功',
            nzContent: '设置系统参数成功！'
          })
        }
      })
    }
  }
  submitMatlCodeForm(){
    for (const i in this.matlcodeValidateForm.controls) {
      this.matlcodeValidateForm.controls[ i ].markAsDirty();
      this.matlcodeValidateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.matlcodeValidateForm.valid){
      this.settingService.putMatlcodeRules(this.matlcodeValidateForm.value).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '设置成功',
            nzContent: '设置材料代码规则成功！'
          })
        }
      })
    }
  }
  formatDate(control){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
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
    formData.append('logo', this.fileList[0]);
    this.settingService.uploadImg(formData).subscribe(
      (event: {}) => {
        this.uploading = false;
        this.msg.success('上传成功！');
        this.editimg = false;
        this.avatarUrl = null;
        this.fileList = [];
        this.settingService.getLogo().subscribe((res) => {
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
}
