import {Component, OnInit, TemplateRef} from '@angular/core';
import {DesignInputService} from './designInput.service';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from '../../../core/storage/storage.service';
import {NzMessageService, NzModalRef, NzModalService} from 'ng-zorro-antd';

@Component({
  selector: 'app-designInput',
  templateUrl: 'designInput.component.html',
  styleUrls: ['./designInput.component.less'],
  providers: [DesignInputService]
})
export class DesignInputComponent implements OnInit {
  validateForm: FormGroup;
  validateForm1: FormGroup;
  validateForm2: FormGroup;
  prodnameValidateForm: FormGroup;
  wmediaValidateForm: FormGroup;
  decoValidateForm: FormGroup;

  constructor(public fb: FormBuilder, public designInputService: DesignInputService,public _storage: SessionStorageService,public msg:NzMessageService,public modalService: NzModalService) {
  }
  deconames = [];
  prodnames = [];
  types = [
    "I",
    "II",
    "III"
  ];
  stands = [];
  designations = [];
  wmedias = [];
  installtypes = [
    {
      label: "立式", value: {name: "立式", ename: "Vertical"}
    },
    {
      label: "卧式", value: {name: "卧式", ename: "Horizontal"}
    }
  ];
  supptypes = [
    {
      label: "裙座", value: {name: "裙座", ename: "Shirt Shell"}
    },
    {
      label: "鞍座", value: {name: "鞍座", ename: "Saddle"}
    },
    {
      label: "支腿", value: {name: "支腿", ename: "Leg"}
    },
    {
      label: "耳座", value: {name: "耳座", ename: "Ear Seat"}
    },
    {
      label: "支座", value: {name: "支座", ename: "Support"}
    },
  ];
  channelnames = [
    {
      label: "整体",value:{name:"整体",ename: "Integer"},selected:false
    },{
      label: "补强圈",value:{name:"补强圈",ename: "Reinforcing Ring"},selected:false
    },{
      label: "夹套",value:{name:"夹套",ename: "Jacket"},selected:false
    },{
      label: "夹层",value:{name:"夹层",ename: "Interlayer"},selected:false
    },{
      label: "内筒",value:{name:"内筒",ename: "Inner Cylinder"},selected:false
    },{
      label: "外筒",value:{name:"外筒",ename: "Outshell Cylinder"},selected:false
    },{
      label: "外筒",value:{name:"外筒",ename: "Outshell Cylinder"},selected:false
    },{
      label: "管程",value:{name:"管程",ename: "TubeSide"},selected:false
    },{
      label: "管程I",value:{name:"管程I",ename: "TubeSide I"},selected:false
    },{
      label: "管程II",value:{name:"管程II",ename: "TubeSide II"},selected:false
    },{
      label: "管程III",value:{name:"管程III",ename: "TubeSide III"},selected:false
    },{
      label: "管程IV",value:{name:"管程IV",ename: "TubeSide IV"},selected:false
    },{
      label: "管程V",value:{name:"管程V",ename: "TubeSide V"},selected:false
    },{
      label: "管程VI",value:{name:"管程VI",ename: "TubeSide VI"},selected:false
    },{
      label: "管程VII",value:{name:"管程VII",ename: "TubeSide VII"},selected:false
    },{
      label: "管程VIII",value:{name:"管程VIII",ename: "TubeSide VIII"},selected:false
    },{
      label: "管程IX",value:{name:"管程VIII",ename: "TubeSide IX"},selected:false
    },{
      label: "管程X",value:{name:"管程VIII",ename: "TubeSide X"},selected:false
    },{
      label: "管程XI",value:{name:"管程VIII",ename: "TubeSide XI"},selected:false
    },{
      label: "管程XII",value:{name:"管程VIII",ename: "TubeSide XII"},selected:false
    },{
      label: "壳程",value:{name:"壳程",ename: "Shell Side"},selected:false
    },{
      label: "壳程I",value:{name:"壳程I",ename: "Shell Side I"},selected:false
    },{
      label: "壳程II",value:{name:"壳程II",ename: "Shell Side II"},selected:false
    },{
      label: "壳程III",value:{name:"壳程III",ename: "Shell Side III"},selected:false
    },{
      label: "壳程IV",value:{name:"壳程IV",ename: "Shell Side IV"},selected:false
    },{
      label: "壳程V",value:{name:"壳程V",ename: "Shell Side V"},selected:false
    },{
      label: "壳程VI",value:{name:"壳程VI",ename: "Shell Side VI"},selected:false
    },{
      label: "壳程VII",value:{name:"壳程VII",ename: "Shell Side VII"},selected:false
    },{
      label: "壳程VIII",value:{name:"壳程VIII",ename: "Shell Side VIII"},selected:false
    },{
      label: "壳程IX",value:{name:"壳程IX",ename: "Shell Side IX"},selected:false
    },{
      label: "壳程X",value:{name:"壳程X",ename: "Shell Side X"},selected:false
    },{
      label: "壳程XI",value:{name:"壳程XI",ename: "Shell Side XI"},selected:false
    },{
      label: "壳程XII",value:{name:"壳程XII",ename: "Shell Side XII"},selected:false
    },    {
      label: "I通道",value:{name:"I通道",ename: "I gallery"},selected:false
    },{
      label: "II通道",value:{name:"II通道",ename: "II gallery"},selected:false
    },{
      label: "III通道",value:{name:"III通道",ename: "III gallery"},selected:false
    },{
      label: "IV通道",value:{name:"IV通道",ename: "IVgallery"},selected:false
    },{
      label: "V通道",value:{name:"V通道",ename: "V gallery"},selected:false
    },{
      label: "VI通道",value:{name:"VI通道",ename: "VI gallery"},selected:false
    },{
      label: "VII通道",value:{name:"VII通道",ename: "VII gallery"},selected:false
    },{
      label: "VIII通道",value:{name:"VIII通道",ename: "VIII gallery"},selected:false
    },{
      label: "IX通道",value:{name:"IX通道",ename: "IX gallery"},selected:false
    },{
      label: "X通道",value:{name:"X通道",ename: "X gallery"},selected:false
    },{
      label: "XI通道",value:{name:"XI通道",ename: "XI gallery"},selected:false
    },{
      label: "XII通道",value:{name:"XII通道",ename: "XII gallery"},selected:false
    }
  ];
  pttypes = [
    {
      label:"气压试验",value:{name:"气压试验",ename:"Pneumatic Test"}
    },
    {
      label:"水压试验",value:{name:"水压试验",ename:"Hydraulic"}
    },
    {
      label:"气液混合",value:{name:"气液混合",ename:"Pneumatic-hydrostatic"}
    },
    {
      label:"/",value:{name:"/",ename:"/"}
    },
  ];
  insultypes = [
    {
      label:"真空粉末绝热",value:{name:"真空粉末绝热",ename:"Vacum Powder Insulation"}
    },
    {
      label:"聚氨酯",value:{name:"真空粉末绝热",ename:"Polyurethane"}
    }
  ];
  leaktests = [
    {
      name:"氦检漏试验",value:{name:"氦检漏试验",ename:"Helium mass spectrometer leak detection"}
    },
    {
      name:"气密性试验",value:{name:"气密性试验",ename:"Gas-tightness test"}
    },
    {
      name:"气密性试验和氦检漏试验",value:{name:"气密性试验和氦检漏试验",ename:"Gas-tightness test and helium mass spectrometer leak detection"}
    },
    {
      name:"氨检漏试验",value:{name:"氨检漏试验",ename:"Ammonia leak test"}
    },
    {
      name:"卤素检漏试验",value:{name:"卤素检漏试验",ename:"Halogen leak test"}
    },
    {
      name:"/",value:{name:"/",ename:"/"}
    }
  ];//无
  ngOnInit(): void {
    this.designInputService.getDeconame().then(res=>{
      if(res['result']=="success"){
        this.deconames = res['data'];
      }
    });
    this.validateForm = this.fb.group({
      dwgno: [null, [Validators.required]],//总图号
      dwgno1: [null],//图号1
      dwgno2: [null],//图号2
      prodname: [null, [Validators.required]],//产品名称
      type: [null, [Validators.required]],//容器类别
      mainstand: [null, [Validators.required]],//产品标准1（主要）
      minorstand: [null, [Validators.required]],//产品标准2（次要）
      deservicelife: [null, [Validators.required]],//设计使用年限
      weight: [null, [Validators.required]],//设备重量
      chweight: [null, [Validators.required]],//充装重量
      installtype: [null, [Validators.required]],//安装型式
      supptype: [null, [Validators.required]],//支座型式
      insultype: [null, [Validators.required]],//保温绝热方式
      ndetype: [null, [Validators.required]],//无损检验方法
      nderatio: [null, [Validators.required]],//无损检测比例
      crytank: ["否", [Validators.required]],//低温贮槽
      testplatesitu: ["无", [Validators.required]],//试板情况
      httype: ["--", [Validators.required]],//热处理种类
      httsetplate: ["无", [Validators.required]],//热处理试板
      httemp: [null, [Validators.required]],//热处理温度
      saferel: ["无", [Validators.required]],//安全泄放装置
      analyde: ["无", [Validators.required]],//按疲劳分析设置
      pvclass: [null, [Validators.required]],//换热面积
      unit: ['/', [Validators.required]],//换热面积单位
      channelnum: [1, [Validators.required]],//通道数
      proheight:[0, [Validators.required]],//产品总高
      length:[0, [Validators.required]],//筒体长度
      designdate: [null, [Validators.required]],//设计日期
      deconame: [this.deconames[0], [Validators.required]]//设计单位名称
    });
    this.validateForm1 = this.fb.group({
      name:[null, [Validators.required]],
      volume:[null, [Validators.required]],
      innerdia:[null, [Validators.required]],
      shmatl1:[null, [Validators.required]],
      shmatl2:[null, [Validators.required]],
      shmatl3:[null, [Validators.required]],
      shthick1:[null, [Validators.required]],
      shthick2:[null, [Validators.required]],
      shthick3:[null, [Validators.required]],
      liningmatl:[null, [Validators.required]],
      liningthick:[null, [Validators.required]],
      wmedia:[null, [Validators.required]],
      hdthick1:[null, [Validators.required]],
      hdthick2:[null, [Validators.required]],
      maxwpress:[null, [Validators.required]],
      depress:[null, [Validators.required]],
      detemp:[null, [Validators.required]],
      wpress:[null, [Validators.required]],
      wtemp:[null, [Validators.required]],
      testpress:[null, [Validators.required]],
      leaktest:[null, [Validators.required]],
      leaktestp:[null, [Validators.required]],
      pttype:["/", [Validators.required]],
    });
    this.validateForm2 = this.fb.group({
      name:[null, [Validators.required]],
      volume:[null, [Validators.required]],
      innerdia:[null, [Validators.required]],
      shmatl1:[null, [Validators.required]],
      shmatl2:[null, [Validators.required]],
      shmatl3:[null, [Validators.required]],
      shthick1:[null, [Validators.required]],
      shthick2:[null, [Validators.required]],
      shthick3:[null, [Validators.required]],
      wmedia:[null, [Validators.required]],
      hdthick1:[null, [Validators.required]],
      hdthick2:[null, [Validators.required]],
      maxwpress:[null, [Validators.required]],
      depress:[null, [Validators.required]],
      detemp:[null, [Validators.required]],
      wpress:[null, [Validators.required]],
      wtemp:[null, [Validators.required]],
      testpress:[null, [Validators.required]],
      leaktest:[null, [Validators.required]],
      leaktestp:[null, [Validators.required]],
      pttype:["/", [Validators.required]],
    });
    this.prodnameValidateForm = this.fb.group({
      prodname:[null, [Validators.required]],
      ename:[null, [Validators.required]]
    });
    this.wmediaValidateForm = this.fb.group({
      wmedia:[null, [Validators.required]],
      wmediaen:[null, [Validators.required]]
    });
    this.decoValidateForm = this.fb.group({
      deconame:[null, [Validators.required]],
      edeconame:[null, [Validators.required]],
      delicense:[null, [Validators.required]],
      time:[null, [Validators.required]],
      orgcode:[null, [Validators.required]],
      code:[null],
    });
    this.designInputService.getprodname().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnames = res["data"];
      }
    });
    this.designInputService.getputmaterial().subscribe((res) => {
      if (res["result"] == "success") {
        this.designations = res['data']['designation'];
      }
    });
    this.designInputService.getgetwmedias().subscribe((res)=>{
      if(res['result']=="success"){
        this.wmedias = res['data'];
      }
    });
    this.designInputService.getstand().subscribe((res)=>{
      this.stands = res["data"];
    })
    this.setChannelNum();
  }


  //安全泄放装置
  i = 1;
  saferelEditCache = {};
  saferelDataSet = [];

  addSaferelRow(): void {
    this.i++;
    this.saferelDataSet = [...this.saferelDataSet, {
      "key": `${this.i}`,
      "name": null,
      "model": null,
      "spec": null,
      "qty": null,
    }];
    this.updateSaferelsaferelEditCache();
    this.saferelEditCache[`${this.i}`].edit = true;
  }

  deleteSaferelRow(i: string): void {
    const saferelDataSet = this.saferelDataSet.filter(d => d.key !== i);
    this.saferelDataSet = saferelDataSet;
  }


  startSaferelEdit(key: string): void {
    this.saferelEditCache[key].edit = true;
  }

  cancelSaferelEdit(key: string): void {
    this.saferelEditCache[key].edit = false;
  }

  saveSaferelEdit(key: string): void {
    const index = this.saferelDataSet.findIndex(item => item.key === key);
    Object.assign(this.saferelDataSet[index], this.saferelEditCache[key].data);
    // this.saferelDataSet[ index ] = this.saferelEditCache[ key ].data;
    this.saferelEditCache[key].edit = false;
  }

  updateSaferelsaferelEditCache(): void {
    this.saferelDataSet.forEach(item => {
      if (!this.saferelEditCache[item.key]) {
        this.saferelEditCache[item.key] = {
          edit: false,
          data: {...item}
        };
      }
    });
  }

  dataSet = [];
  setChannelNum(){
    this.dataSet = [];
    for (let i = 3; i <= this.validateForm.value.channelnum; i++) {
      this.dataSet.push({
        key    : i,
        name   : null,
        volume : null,
        innerdia : null,
        shmatl1 : null,
        shmatl2 : null,
        shmatl3 : null,
        shthick1 : null,
        shthick2 : null,
        shthick3 : null,
        wmedia:null,
        hdthick1:null,
        hdthick2:null,
        maxwpress:null,
        depress:null,
        detemp:null,
        wpress:null,
        wtemp:null,
        testpress:null,
        leaktest:null,
        leaktestp:null,
        pttype:null
      });
    }
  }
  submitForm(){
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    let channelData;
    if(this.validateForm.value.channelnum>0){
      for (const i in this.validateForm1.controls) {
        this.validateForm1.controls[ i ].markAsDirty();
        this.validateForm1.controls[ i ].updateValueAndValidity();
      }
      channelData = [
        {
          "name":this.validateForm1.value.name.name,
          "ename":this.validateForm1.value.name.ename,
          "volume":this.validateForm1.value.volume,
          "innerdia":this.validateForm1.value.innerdia,
          "shmatl1":this.validateForm1.value.shmatl1,
          "shmatl2":this.validateForm1.value.shmatl2,
          "shmatl3":this.validateForm1.value.shmatl3,
          "shthick1":this.validateForm1.value.shthick1,
          "shthick2":this.validateForm1.value.shthick2,
          "shthick3":this.validateForm1.value.shthick3,
          "liningmatl":this.validateForm1.value.liningmatl,
          "liningthick":this.validateForm1.value.liningthick,
          "wmedia":this.validateForm1.value.wmedia.name,
          "hdthick1":this.validateForm1.value.hdthick1,
          "hdthick2":this.validateForm1.value.hdthick2,
          "maxwpress":this.validateForm1.value.maxwpress,
          "depress":this.validateForm1.value.depress,
          "detemp":this.validateForm1.value.detemp,
          "wpress":this.validateForm1.value.wpress,
          "wtemp":this.validateForm1.value.wtemp,
          "testpress":this.validateForm1.value.testpress,
          "leaktest":this.validateForm1.value.leaktest.name,
          "eleaktest":this.validateForm1.value.leaktest.value.ename,
          "leaktestp":this.validateForm1.value.leaktestp,
          "pttype":this.validateForm1.value.pttype.name,
          "epttype":this.validateForm1.value.pttype.ename
        }
      ];
    }
    if(this.validateForm.value.channelnum>1){
      for (const i in this.validateForm2.controls) {
        this.validateForm2.controls[ i ].markAsDirty();
        this.validateForm2.controls[ i ].updateValueAndValidity();
      }
      channelData.push(
        {
          "name":this.validateForm2.value.name.name,
          "ename":this.validateForm2.value.name.ename,
          "volume":this.validateForm2.value.volume,
          "innerdia":this.validateForm2.value.innerdia,
          "shmatl1":this.validateForm2.value.shmatl1,
          "shmatl2":this.validateForm2.value.shmatl2,
          "shmatl3":this.validateForm2.value.shmatl3,
          "shthick1":this.validateForm2.value.shthick1,
          "shthick2":this.validateForm2.value.shthick2,
          "shthick3":this.validateForm2.value.shthick3,
          "liningmatl":this.validateForm2.value.liningmatl,
          "liningthick":this.validateForm2.value.liningthick,
          "wmedia":this.validateForm2.value.wmedia.name,
          "hdthick1":this.validateForm2.value.hdthick1,
          "hdthick2":this.validateForm2.value.hdthick2,
          "maxwpress":this.validateForm2.value.maxwpress,
          "depress":this.validateForm2.value.depress,
          "detemp":this.validateForm2.value.detemp,
          "wpress":this.validateForm2.value.wpress,
          "wtemp":this.validateForm2.value.wtemp,
          "testpress":this.validateForm2.value.testpress,
          "leaktest":this.validateForm2.value.leaktest.name,
          "eleaktest":this.validateForm2.value.leaktest.value.ename,
          "leaktestp":this.validateForm2.value.leaktestp,
          "pttype":this.validateForm2.value.pttype.name,
          "epttype":this.validateForm2.value.pttype.ename
        });
    }
    if(this.validateForm.value.channelnum>2){
      for(let data of this.dataSet){
        data.ename = data.name.ename;
        data.name = data.name.name;
        data.epttype = data.pttype.ename;
        data.pttype = data.pttype.name;
        channelData.push(data);
        for(let i in data){
          if(!data[i]){
            this.msg.error("请完整填写通道数据后重新提交！")
            return;
          }
        }
      }
    }
    //检查数据是否填写完整
    let valid = this.validateForm.valid;
    if(this.validateForm.value.channelnum>1){
      valid = this.validateForm.valid && this.validateForm1.valid && this.validateForm2.valid;
    }else if(this.validateForm.value.channelnum>0){
      valid = this.validateForm.valid && this.validateForm1.valid;
    }
    if(valid){
      if(this.validateForm.value.saferel=="有"){//如果有安全泄放装置
        this.designInputService.putSaferel({dwgno:this.validateForm.value.dwgno,data:this.saferelDataSet}).subscribe((res)=>{
          if(res["result"]=="success"){
            this.designInputService.putChannel({dwgno:this.validateForm.value.dwgno,data:channelData}).subscribe((res)=>{
              if(res["result"]=="success"){
                this.putProduce()
              }
            })
          }
        })
      }else{
        this.designInputService.putChannel({dwgno:this.validateForm.value.dwgno,data:channelData}).subscribe((res)=>{
          if(res["result"]=="success"){
            this.putProduce()
          }
        })
      }
    }
  }
  resetForm(){
    this.validateForm.reset();
    this.validateForm1.reset();
    this.validateForm2.reset();
    this.validateForm.controls['channelnum'].setValue(1);
    this.setChannelNum();
  }
  changeSelectedStatus(){ //隐藏已选择选项
    for(let i of this.channelnames)
      i.selected = false;
    for(let i of this.channelnames){
      if(this.validateForm1.value.name)
        if(i.label == this.validateForm1.value.name.name)
          i.selected = true;
      if(this.validateForm2.value.name)
        if(i.label == this.validateForm2.value.name.name)
          i.selected = true;
      for(let data of this.dataSet){
        if(data.name)
          if(data.name.name == i.label)
            i.selected = true;
      }
    }
  }
  putProduce(){
    this.designInputService.putProduce({
      "user":this._storage.get("username"),
      "dwgno": this.validateForm.value.dwgno,//总图号
      "dwgno1":this.validateForm.value.dwgno1,//图号1
      "dwgno2":this.validateForm.value.dwgno2,//图号2
      "prodname": this.validateForm.value.prodname,//产品名称
      "type": this.validateForm.value.type,//容器类别
      "mainstand": this.validateForm.value.mainstand,//产品标准1（主要）
      "minorstand": this.validateForm.value.minorstand,//产品标准2（次要）
      "deservicelife": this.validateForm.value.deservicelife,//设计使用年限
      "weight": this.validateForm.value.weight,//设备重量
      "chweight": this.validateForm.value.chweight,//充装重量
      "installtype": this.validateForm.value.installtype.name,//安装型式
      "einstalltype": this.validateForm.value.installtype.ename,//安装型式英文
      "supptype": this.validateForm.value.supptype.name,//支座型式
      "esupptype": this.validateForm.value.supptype.ename,//支座型式英文
      "insultype": this.validateForm.value.insultype.name,//保温绝热方式
      "einsultype": this.validateForm.value.ename,//保温绝热方式英文
      "ndetype": this.validateForm.value.ndetype,//无损检验方法
      "nderatio": this.validateForm.value.nderatio,//无损检测比例
      "crytank": this.validateForm.value.crytank,//低温贮槽
      "testplatesitu": this.validateForm.value.testplatesitu,//试板情况
      "httype": this.validateForm.value.httype,//热处理种类
      "httsetplate": this.validateForm.value.httsetplate,//热处理试板
      "httemp": this.validateForm.value.httemp,//热处理温度
      "saferel": this.validateForm.value.saferel,//安全泄放装置
      "analyde": this.validateForm.value.analyde,//按疲劳分析设置
      "pvclass": this.validateForm.value.pvclass,//换热面积
      "unit":this.validateForm.value.unit,//换热面积单位
      "proheight":this.validateForm.value.proheight,//产品总高
      "length":this.validateForm.value.length,//筒体长度
      "designdate": this.validateForm.value.designdate,//设计日期
      "deconame": this.validateForm.value.deconame//设计单位名称
    }).subscribe((res)=>{
      if(res['result']=="success"){
        this.msg.success("提交成功！");
        this.resetForm();
      }
    })
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
    if(which == "prodname"){
      for (const i in this.prodnameValidateForm.controls) {
        this.prodnameValidateForm.controls[ i ].markAsDirty();
        this.prodnameValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.prodnameValidateForm.valid) {
        this.designInputService.putProdname(this.prodnameValidateForm.value.prodname,this.prodnameValidateForm.value.ename).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("提交成功！");
            this.prodnameValidateForm.reset();
            this.designInputService.getprodname().subscribe((res) => {
              if (res["result"] == "success") {
                this.prodnames = res["data"];
              }
            });
          }
        })
      }
    }
    if(which == "wmedia"){
      for (const i in this.wmediaValidateForm.controls) {
        this.wmediaValidateForm.controls[ i ].markAsDirty();
        this.wmediaValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.wmediaValidateForm.valid) {
        this.designInputService.putWmedia(this.wmediaValidateForm.value.wmedia,this.wmediaValidateForm.value.wmediaen).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("提交成功！");
            this.wmediaValidateForm.reset();
            this.designInputService.getgetwmedias().subscribe((res)=>{
              if(res['result']=="success"){
                this.wmedias = res['data'];
              }
            });
          }
        })
      }
    }
    if(which == "deco"){
      for (const i in this.decoValidateForm.controls) {
        this.decoValidateForm.controls[ i ].markAsDirty();
        this.decoValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.decoValidateForm.valid) {
        this.designInputService.putDeco(this.decoValidateForm.value).subscribe((res)=>{
          if(res["result"]=="success"){
            this.msg.success("提交成功！");
            this.decoValidateForm.reset();
            this.designInputService.getDeconame().then(res=>{
              if(res['result']=="success"){
                this.deconames = res['data'];
              }
            });
          }
        })
      }
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
}

