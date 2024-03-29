import {Component, OnInit, TemplateRef} from '@angular/core';
import {MaterialDistributeService} from "./materialDistribute.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-materialDistribute',
  templateUrl: 'materialDistribute.component.html',
  styleUrls: ['./materialDistribute.component.less'],
  providers: [MaterialDistributeService]
})
export class MaterialDistributeComponent implements OnInit {
  public prodno:any;
  validateForm: FormGroup;
  partsnameValidateForm: FormGroup;
  copyvalidateForm: FormGroup;
  status = false;
  i=0;
  matlcode:any;
  isLoading = false;
  partsnames = [];
  designations = [];
  users = [];
  username2name = {};
  specs = [];
  ruleindex = null;
  rule = null;
  copyprodnos = []
  onSpecInput(value: string): void { //当规格输入时展开选项
    this.specs = value ? [
      value,
      "φ"+value,
      "δ="+value,
      "EHA"+value,
      "∠"+value,
      "DHB"+value,
      "T"+value,
      "12~40目",
      "10~60目"
    ]:[
      "φ"+value,
      "δ="+value,
      "EHA"+value,
      "∠"+value,
      "DHB"+value,
      "T"+value,
      "12~40目",
      "10~60目"
    ];
  }

  ngOnInit(): void {
    this.materialDistributeService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodno = res['data'];
      }
    });
    this.materialDistributeService.getindexbymatlcoderules().subscribe(res => {
      if (res['result'] == "success") {
        this.ruleindex = res['index'];
        this.rule = res['welding']
      }
    })
    this.materialDistributeService.getindexbymatlcoderules().subscribe(res => {
      if(res['result'] == "success"){
        this.matlcode = res;
      }
    })
    this.materialDistributeService.getPartsname().subscribe(res=>{
      if(res['result']=='success'){
        this.partsnames = res['data'];
      }
    });
    this.materialDistributeService.getputmaterial().subscribe(res=>{
      if(res['result']=='success'){
        this.designations = res['data']['designation'];
      }
    });
    this.materialDistributeService.getuserform().subscribe(res=>{
      if(res['result']=='success'){
        this.users = [];
        for(let user of res['data']){
          if(this.checkRole(user)){
            this.users.push(user);
            this.username2name[user.username] = user.name;
          }
        }
      }
    });
    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "prodname":[null],
      "dwgno":[null],
    });
    this.copyvalidateForm = this.fb.group({
      "prodno":[null, [Validators.required]]
    })
    this.partsnameValidateForm = this.fb.group({
      partsname:[null, [Validators.required]],
      enpartsname:[null, [Validators.required]]
    });
    this.updateEditCache();
  }
  checkRole(user:any){
    return (user.role==55 ||  user.role==56 ||user.role2==55 ||  user.role2==56 ||user.role3==55 ||  user.role3==56 ||user.role4==55 ||  user.role4==56 ||user.role5==55 ||  user.role5==56);
  }

  searchData(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.editCache = {};
      this.materialDistributeService.getdistribute(this.validateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['prodname'].setValue(res['prodname']);
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
          this.dataSet = res['data'];
          this.status = true;
          for(this.i = 0;this.i < this.dataSet.length;this.i++){
            this.dataSet[this.i]['key'] = `${this.i}`;
          }
          this.updateEditCache();
        }
      })
    }else{
      this.status = false;
    }
  }
  constructor(public materialDistributeService: MaterialDistributeService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  addRow(): void {
    this.i++;
    this.dataSet = [ ...this.dataSet, {
      "key"    : `${this.i}`,
      "spartname":null,//零件名称
      "spec":null,//规格
      "dimension":null,//尺寸
      "partno":null,//件号
      "designation":null,//牌号
      "qty":null,//数量
      "codedmarking":null,//入库编号
      "issuedate":null,//发料日期
      "picker":null,//领料人
      "ispresspart":2,//是否为主要受压元件
      "weldno":null,//焊缝号
      "returnqty":null,//退回数量
      "issuematl":this._storage.get("username") //发料人
    } ];
    this.updateEditCache();
    this.editCache[ `${this.i}` ].edit = true;
  }


  deleteRow(i: string): void {
    const dataSet = this.dataSet.filter(d => d.key !== i);
    this.dataSet = dataSet;
  }

  editCache = {};
  dataSet = [];

  startEdit(key: string): void {
    this.screeningCodedmarking(key,false);
    this.editCache[ key ].edit = true;
  }

  cancelEdit(key: string): void {
    this.editCache[ key ].edit = false;
  }

  saveEdit(key: string): void {
    const index = this.dataSet.findIndex(item => item.key === key);
    Object.assign(this.dataSet[ index ], this.editCache[ key ].data);
    // this.dataSet[ index ] = this.editCache[ key ].data;
    this.editCache[ key ].edit = false;
  }

  updateEditCache(): void {
    this.dataSet.forEach(item => {
      if (!this.editCache[ item.key ]) {
        this.editCache[ item.key ] = {
          edit: false,
          data: { ...item }
        };
      }
    });
  }

  putdistribute(){
    for(let item in this.editCache){
      if(this.editCache[item].edit){
        this.message.error("您有尚未保存的数据行，请保存后再提交！");
        return;
      }
    }
    if(this.dataSet.length == 0){
      this.message.error("您尚未填写任何数据，本次提交无效！");
      return;
    }
    for(let j = 0;j<this.dataSet.length;j++){
      for(let i in this.dataSet[j]){
        if(this.dataSet[j][i]==null && i != 'returnqty' && i!='weldno'){
          this.message.error("您有尚未填写的数据，请填写完整后再提交！");
          return;
        }
      }
    }
    this.materialDistributeService.putdistribute({
      prodno:this.validateForm.controls['prodno'].value,
      data:this.dataSet
    }).subscribe((res)=>{
      if(res['result']=="success"){
        let modal = this.modalService.success({
          nzTitle: '提交成功',
          nzContent: '发放记录提交成功！'
        });
      }else{
        this.message.error("提交失败，请稍后再试！")
      }
    })
  }

  savedistribute(){
    for(let item in this.editCache){
      if(this.editCache[item].edit){
        this.message.error("您有尚未保存的数据行，请保存后再提交！");
        return;
      }
    }
    this.materialDistributeService.savedistribute({
      prodno:this.validateForm.controls['prodno'].value,
      data:this.dataSet
    }).subscribe((res)=>{
      if(res['result']=="success"){
        this.message.success("发放记录保存成功！");
      }else{
        this.message.error("保存失败，请稍后再试！")
      }
    })
  }

  formatInDate(key) { //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if (monthDay.test(this.editCache[key].data.issuedate)) {
      this.editCache[key].data.issuedate = (new Date().getFullYear() + "-" + this.editCache[key].data.issuedate);
    } else if (!yearMonthDay.test(this.editCache[key].data.issuedate)) {
      this.editCache[key].data.issuedate = null;
    }
  }

  public tplModal: NzModalRef;
  public tplModalButtonLoading = false;
  createTplModal(tplTitle: TemplateRef<{}>, tplContent: TemplateRef<{}>, tplFooter: TemplateRef<{}>): void {
    this.tplModal = this.modalService.create({
      nzTitle: tplTitle,
      nzContent: tplContent,
      nzFooter: tplFooter,
      nzMaskClosable: false,
      nzClosable: true,
      nzWidth: 700,
      nzOnOk: null
    });
  }

  destroyTplModal(): void {
    this.tplModalButtonLoading = true;
    window.setTimeout(() => {
      this.tplModalButtonLoading = false;
      this.tplModal.destroy();
    }, 1000);
  }

  submitInfo(which){
    if(which == "partsname"){
      for (const i in this.partsnameValidateForm.controls) {
        this.partsnameValidateForm.controls[ i ].markAsDirty();
        this.partsnameValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.partsnameValidateForm.valid){
        this.materialDistributeService.addPartsname(this.partsnameValidateForm.controls['partsname'].value,this.partsnameValidateForm.controls['enpartsname'].value).subscribe(res=>{
          if(res['result'] == "success"){
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.materialDistributeService.getPartsname().subscribe(res=>{
              if(res['result']=='success'){
                this.partsnames = res['data'];
              }
            });
            this.destroyTplModal();
            this.partsnameValidateForm.reset();
            this.partsnameValidateForm.clearValidators();
          }else{
            this.message.error("添加失败，请稍后重试！");
          }
        });
      }
    }else if(which == "copyfrom"){
      let dataset2 = [];
      for(let i =0;i<this.dataSet2.length;i++){
        if(this.mapOfCheckedId[i]) {
          this.i++;
          this.dataSet2[i].key = `${this.i}`;
          dataset2.push(this.dataSet2[i]);
        }
      }
      this.dataSet = [...this.dataSet,...dataset2];
      this.updateEditCache();
      this.tplModal.destroy();
    }
  }

  codedmarkingDisplay = [];
  screeningCodedmarking(key,flag){//根据牌号筛选codedmarking
    let des = this.editCache[key].data.designation;
    if(flag)this.editCache[key].data.codedmarking = null;
    if (des != null) {
      if (des != null){
          this.isLoading = true;
          this.materialDistributeService.getCodedmarkingByDesignation(des).subscribe(res => {
          if (res['result'] == "success") {
            let data = res['data'];
            this.codedmarkingDisplay = data.filter(item=>item[this.matlcode['index']-1] != this.matlcode['welding'])//若不为焊材，显示
            this.isLoading = false;
          }else{
            this.isLoading = false;
          }
        },err=>{
          this.isLoading = false;
        });
      }else this.codedmarkingDisplay = [];
    } else this.codedmarkingDisplay = [];
  }
  dataSet2 = [];
  dataSet2Display = [];
  status2 = true;
  copy(){
    this.materialDistributeService.getprodnosbydwgno(this.validateForm.controls['dwgno'].value).subscribe(res=>{
      if(res['result'] == "success"){
        this.copyprodnos = res['data'];
        if(this.copyprodnos.length>0){
          this.copyvalidateForm.controls['prodno'].setValue(this.copyprodnos[0]);
          this.searchData2(null);
        }
      }
    })
  }
  searchData2(e): void {
    if(e)
      e.preventDefault();
    for (const i in this.copyvalidateForm.controls) {
      this.copyvalidateForm.controls[ i ].markAsDirty();
      this.copyvalidateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.copyvalidateForm.valid){
      this.isAllDisplayDataChecked = false;
      this.mapOfCheckedId = {};
      this.status2 = false;
      this.dataSet2 = [];
      this.materialDistributeService.getdistribute(this.copyvalidateForm.controls['prodno'].value).subscribe((res) => {
        if(res['result']=="success"){
          this.dataSet2 = res['data'].filter(data=>!data.codedmarking|| data.codedmarking.length<this.ruleindex ||data.codedmarking[this.ruleindex-1] != this.rule);
          for(let i = 0;i<this.dataSet2.length;i++){
            this.mapOfCheckedId[i] = false;
          }
          this.status2 = true;
        }
      })
    }else{
      this.status2 = false;
    }
  }
  isAllDisplayDataChecked = false;
  mapOfCheckedId: { [key: string]: boolean } = {};
  checkAll(){
    if(this.isAllDisplayDataChecked){
      for(let i = 0;i<this.dataSet2.length;i++){
        this.mapOfCheckedId[i] = true;
      }
    }else{
      for(let i = 0;i<this.dataSet2.length;i++){
        this.mapOfCheckedId[i] = false;
      }
    }
  }
  checkAll2(){
    if(!this.isAllDisplayDataChecked){
      let flag = true;
      for(let i = 0;i<this.dataSet2.length;i++){
        if(this.mapOfCheckedId[i] == false)
          flag = false;
      }
      if(flag) this.isAllDisplayDataChecked = true;
    }
    if(this.isAllDisplayDataChecked){
      let flag = true;
      for(let i = 0;i<this.dataSet2.length;i++){
        if(this.mapOfCheckedId[i] == false)
          flag = false;
      }
      if(!flag) this.isAllDisplayDataChecked = false;
    }
  }
}
