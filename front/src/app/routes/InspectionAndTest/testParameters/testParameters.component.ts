import {Component, OnInit, TemplateRef} from '@angular/core';
import {TestParametersService} from './testParameters.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-testParameters',
  templateUrl: 'testParameters.component.html',
  styleUrls: ['./testParameters.component.less'],
  providers: [TestParametersService]
})
export class TestParametersComponent implements OnInit {
  public dwgnos = [];
  public exitnos = [];
  public dataModel = [];
  public loading = false;
  public pparts = [
      {
        label: "整体",value:{name:"整体",ename: "Integer"}
      },{
        label: "补强圈",value:{name:"补强圈",ename: "Reinforcing Ring"}
      },{
        label: "夹套",value:{name:"夹套",ename: "Jacket"}
      },{
        label: "夹层",value:{name:"夹层",ename: "Interlayer"}
      },{
        label: "内筒",value:{name:"内筒",ename: "Inner Cylinder"}
      },{
        label: "外筒",value:{name:"外筒",ename: "Outshell Cylinder"}
      },{
        label: "管程",value:{name:"管程",ename: "TubeSide"}
      },{
        label: "管程Ⅰ",value:{name:"管程Ⅰ",ename: "TubeSide Ⅰ"}
      },{
        label: "管程Ⅱ",value:{name:"管程Ⅱ",ename: "TubeSide Ⅱ"}
      },{
        label: "管程Ⅲ",value:{name:"管程Ⅲ",ename: "TubeSide Ⅲ"}
      },{
        label: "管程Ⅳ",value:{name:"管程Ⅳ",ename: "TubeSide Ⅳ"}
      },{
        label: "管程Ⅴ",value:{name:"管程Ⅴ",ename: "TubeSide Ⅴ"}
      },{
        label: "管程Ⅵ",value:{name:"管程Ⅵ",ename: "TubeSide Ⅵ"}
      },{
        label: "管程Ⅶ",value:{name:"管程Ⅶ",ename: "TubeSide Ⅶ"}
      },{
        label: "管程Ⅷ",value:{name:"管程Ⅷ",ename: "TubeSide Ⅷ"}
      },{
        label: "管程Ⅸ",value:{name:"管程Ⅷ",ename: "TubeSide Ⅸ"}
      },{
        label: "管程Ⅹ",value:{name:"管程Ⅷ",ename: "TubeSide Ⅹ"}
      },{
        label: "管程Ⅺ",value:{name:"管程Ⅷ",ename: "TubeSide Ⅺ"}
      },{
        label: "管程Ⅻ",value:{name:"管程Ⅷ",ename: "TubeSide Ⅻ"}
      },{
        label: "壳程",value:{name:"壳程",ename: "Shell Side"}
      },{
        label: "壳程Ⅰ",value:{name:"壳程Ⅰ",ename: "Shell Side Ⅰ"}
      },{
        label: "壳程Ⅱ",value:{name:"壳程Ⅱ",ename: "Shell Side Ⅱ"}
      },{
        label: "壳程Ⅲ",value:{name:"壳程Ⅲ",ename: "Shell Side Ⅲ"}
      },{
        label: "壳程Ⅳ",value:{name:"壳程Ⅳ",ename: "Shell Side Ⅳ"}
      },{
        label: "壳程Ⅴ",value:{name:"壳程Ⅴ",ename: "Shell Side Ⅴ"}
      },{
        label: "壳程Ⅵ",value:{name:"壳程Ⅵ",ename: "Shell Side Ⅵ"}
      },{
        label: "壳程Ⅶ",value:{name:"壳程Ⅶ",ename: "Shell Side Ⅶ"}
      },{
        label: "壳程Ⅷ",value:{name:"壳程Ⅷ",ename: "Shell Side Ⅷ"}
      },{
        label: "壳程Ⅸ",value:{name:"壳程Ⅸ",ename: "Shell Side Ⅸ"}
      },{
        label: "壳程Ⅹ",value:{name:"壳程Ⅹ",ename: "Shell Side Ⅹ"}
      },{
        label: "壳程Ⅺ",value:{name:"壳程Ⅺ",ename: "Shell Side Ⅺ"}
      },{
        label: "壳程Ⅻ",value:{name:"壳程Ⅻ",ename: "Shell Side Ⅻ"}
      },    {
        label: "Ⅰ通道",value:{name:"Ⅰ通道",ename: "Ⅰ gallery"}
      },{
        label: "Ⅱ通道",value:{name:"Ⅱ通道",ename: "Ⅱ gallery"}
      },{
        label: "Ⅲ通道",value:{name:"Ⅲ通道",ename: "Ⅲ gallery"}
      },{
        label: "Ⅳ通道",value:{name:"Ⅳ通道",ename: "Ⅳgallery"}
      },{
        label: "Ⅴ通道",value:{name:"Ⅴ通道",ename: "Ⅴ gallery"}
      },{
        label: "Ⅵ通道",value:{name:"Ⅵ通道",ename: "Ⅵ gallery"}
      },{
        label: "Ⅶ通道",value:{name:"Ⅶ通道",ename: "Ⅶ gallery"}
      },{
        label: "Ⅷ通道",value:{name:"Ⅷ通道",ename: "Ⅷ gallery"}
      },{
        label: "Ⅸ通道",value:{name:"Ⅸ通道",ename: "Ⅸ gallery"}
      },{
        label: "Ⅹ通道",value:{name:"Ⅹ通道",ename: "Ⅹ gallery"}
      },{
        label: "Ⅺ通道",value:{name:"Ⅺ通道",ename: "Ⅺ gallery"}
      },{
        label: "Ⅻ通道",value:{name:"Ⅻ通道",ename: "Ⅻ gallery"}
      }
    ];
  public testmedias = [
    {
      name:"干燥空气",ename:"Dry air",cl:"-- --"
    },
    {
      name:"氮气",ename:"N₂",cl:"-- --"
    },
    {
      name:"洁净水",ename:"Clean water",cl:"15"
    },
    {
      name:"煤油",ename:"Kerosene",cl:"-- --"
    },
    {
      name:"氨",ename:"NH₃",cl:"-- --"
    },
    {
      name:"氦",ename:"He",cl:"-- --"
    },
  ];

  validateForm: FormGroup;

  channelForms:any;

  constructor(public testParametersService: TestParametersService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }
  ngOnInit(): void {
    this.validateForm  = this.fb.group({
      "prodno":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
    });
    this.testParametersService.getdwgno().subscribe((res)=>{
      if(res['result']=="success"){
        this.dwgnos = res['data'];
      }
    })
    this.testParametersService.getexitno().subscribe((res)=>{
      if(res['result']=="success")
        this.exitnos = res['data']
    })
  }
  link = 0;
  checklink(){
    this.dataModel = [];
    this.channelForms = [];
    if(this.validateForm.controls['prodno'].value!=null) {
      this.testParametersService.check(this.validateForm.controls['prodno'].value).subscribe(res=>{
        if(res['result'] == "success"){
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
          this.validateForm.controls['dwgno'].disable();
          this.link = 2;
        }else if(res['result'] == "error"){
          this.link = 1;
          this.validateForm.controls['dwgno'].enable();
        }else{
          this.link = 0;
          this.validateForm.controls['dwgno'].enable();
        }
      })
    }
  }
  getChannel():void{
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.loading = true;
        this.testParametersService.getPressandLeak(this.validateForm.controls['prodno'].value).subscribe(res=>{
          if(res['result'] == "success"){
            this.channelForms = [];
            this.dataModel = [];
            this.showChannel = false;
            let fbb :FormGroup;
            for(let channel of res['data']) {
              fbb = this.fb.group({//初始化数据
                prodno: [this.validateForm.controls['prodno'].value, [Validators.required]],
                dwgno: [this.validateForm.controls['dwgno'].value, [Validators.required]],
                ppart: [channel.name, [Validators.required]],
                eppart: [this.pparts.filter(item => item.label == channel.name)[0].value.ename, [Validators.required]],
                dated1: [channel['dated1']?channel['dated1']['date']:null],
                dated2: [channel['dated2']?channel['dated2']['date']:null],
                dated3: [channel['dated3']?channel['dated3']['date']:null],
                testmedia: [this.testmedias.filter(item => item.name == channel['testmedia'])[0]],
                etestmedia: [null],
                clcontent: [channel.clcontent]
              });
              let model = {
                  leaktest: channel['leakagestatus'],
                  dated1: {
                    status: 1,//0隐藏，1显示，2禁用
                    press: {
                      date: null,
                      pgaugeno1: null,
                      pgaugeno2: null,
                      dewelltime: null,
                      circutemp: null,
                      mediatemp: null,
                      testpress: channel['testpress'],
                      ppart: null,
                      testmedia: null,
                      range: null
                    },
                    //泄漏参数
                    leak: {
                      date: null,
                      pgaugeno1: null,
                      pgaugeno2: null,
                      dewelltime: null,
                      circutemp: null,
                      mediatemp: null,
                      leaktestp: channel['leaktestp'],
                      ppart: null,
                      testmedia: null,
                    }
                  },
                 dated2: {
                  status: 0,
                  press: {
                    date: null,
                    pgaugeno1: null,
                    pgaugeno2: null,
                    dewelltime: null,
                    circutemp: null,
                    mediatemp: null,
                    testpress: channel['testpress'],
                    ppart: null,
                    testmedia: null,
                    range: null
                  },
                  //泄漏参数
                  leak: {
                    date: null,
                    pgaugeno1: null,
                    pgaugeno2: null,
                    dewelltime: null,
                    circutemp: null,
                    mediatemp: null,
                    leaktestp: channel['leaktestp'],
                    ppart: null,
                    testmedia: null,
                  }
                },
                dated3: {
                  status: 0,
                  press: {
                    date: null,
                    pgaugeno1: null,
                    pgaugeno2: null,
                    dewelltime: null,
                    circutemp: null,
                    mediatemp: null,
                    testpress: channel['testpress'],
                    ppart: null,
                    testmedia: null,
                    range: null
                  },
                  //泄漏参数
                  leak: {
                    date: null,
                    pgaugeno1: null,
                    pgaugeno2: null,
                    dewelltime: null,
                    circutemp: null,
                    mediatemp: null,
                    leaktestp: channel['leaktestp'],
                    ppart: null,
                    testmedia: null,
                  }
                }
              };
              if (channel['dated1'] &&(channel['dated1']['press'] || channel['dated1']['leak'])){
                model.dated1 = {
                  status: 2,//0隐藏，1显示，2禁用
                  press: {
                    date: channel['dated1']['press']?channel['dated1']['press']['date']:null,
                    pgaugeno1: channel['dated1']['press']?channel['dated1']['press']['pgaugeno1']:null,
                    pgaugeno2: channel['dated1']['press']?channel['dated1']['press']['pgaugeno2']:null,
                    dewelltime: channel['dated1']['press']?channel['dated1']['press']['dewelltime']:null,
                    circutemp: channel['dated1']['press']?channel['dated1']['press']['circutemp']:null,
                    mediatemp: channel['dated1']['press']?channel['dated1']['press']['mediatemp']:null,
                    testpress: channel['testpress'],
                    ppart: channel['dated1']['press']?channel['dated1']['press']['ppart']:null,
                    testmedia: channel['dated1']['press']?channel['dated1']['press']['testmedia']:null,
                    range:null
                  },
                  //泄漏参数
                  leak: {
                    date: channel['dated1']['leak']?channel['dated1']['leak']['date']:null,
                    pgaugeno1: channel['dated1']['leak']?channel['dated1']['leak']['pgaugeno1']:null,
                    pgaugeno2: channel['dated1']['leak']?channel['dated1']['leak']['pgaugeno2']:null,
                    dewelltime: channel['dated1']['leak']?channel['dated1']['leak']['dewelltime']:null,
                    circutemp: channel['dated1']['leak']?channel['dated1']['leak']['circutemp']:null,
                    mediatemp: channel['dated1']['leak']?channel['dated1']['leak']['mediatemp']:null,
                    leaktestp: channel['leaktestp'],
                    ppart: channel['dated1']['leak']?channel['dated1']['leak']['ppart']:null,
                    testmedia: channel['dated1']['leak']?channel['dated1']['leak']['testmedia']:null,
                  }
                }
                model.dated2.status = 1;
              }
              if (channel['dated2'] && (channel['dated2']['press'] || channel['dated2']['leak'])){
                model.dated2 = {
                  status: 2,//0隐藏，1显示，2禁用
                  press: {
                    date: channel['dated2']['press']?channel['dated2']['press']['date']:null,
                    pgaugeno1: channel['dated2']['press']?channel['dated2']['press']['pgaugeno1']:null,
                    pgaugeno2: channel['dated2']['press']?channel['dated2']['press']['pgaugeno2']:null,
                    dewelltime: channel['dated2']['press']?channel['dated2']['press']['dewelltime']:null,
                    circutemp: channel['dated2']['press']?channel['dated2']['press']['circutemp']:null,
                    mediatemp: channel['dated2']['press']?channel['dated2']['press']['mediatemp']:null,
                    testpress: channel['testpress'],
                    ppart: channel['dated2']['press']?channel['dated2']['press']['ppart']:null,
                    testmedia: channel['dated2']['press']?channel['dated2']['press']['testmedia']:null,
                    range:null
                  },
                  //泄漏参数
                  leak: {
                    date: channel['dated2']['leak']?channel['dated2']['leak']['date']:null,
                    pgaugeno1: channel['dated2']['leak']?channel['dated2']['leak']['pgaugeno1']:null,
                    pgaugeno2: channel['dated2']['leak']?channel['dated2']['leak']['pgaugeno2']:null,
                    dewelltime: channel['dated2']['leak']?channel['dated2']['leak']['dewelltime']:null,
                    circutemp: channel['dated2']['leak']?channel['dated2']['leak']['circutemp']:null,
                    mediatemp: channel['dated2']['leak']?channel['dated2']['leak']['mediatemp']:null,
                    leaktestp: channel['leaktestp'],
                    ppart: channel['dated2']['leak']?channel['dated2']['leak']['ppart']:null,
                    testmedia: channel['dated2']['leak']?channel['dated2']['leak']['testmedia']:null,
                  }
                }
                model.dated3.status = 1;
              }
              if (channel['dated3'] && (channel['dated3']['press'] || channel['dated3']['leak'])){
                model.dated3 = {
                  status: 2,//0隐藏，1显示，2禁用
                  press: {
                    date: channel['dated3']['press']?channel['dated3']['press']['date']:null,
                    pgaugeno1: channel['dated3']['press']?channel['dated3']['press']['pgaugeno1']:null,
                    pgaugeno2: channel['dated3']['press']?channel['dated3']['press']['pgaugeno2']:null,
                    dewelltime: channel['dated3']['press']?channel['dated3']['press']['dewelltime']:null,
                    circutemp: channel['dated3']['press']?channel['dated3']['press']['circutemp']:null,
                    mediatemp: channel['dated3']['press']?channel['dated3']['press']['mediatemp']:null,
                    testpress: channel['testpress'],
                    ppart: channel['dated3']['press']?channel['dated3']['press']['ppart']:null,
                    testmedia: channel['dated3']['press']?channel['dated3']['press']['testmedia']:null,
                    range:null
                  },
                  //泄漏参数
                  leak: {
                    date: channel['dated3']['leak']?channel['dated3']['leak']['date']:null,
                    pgaugeno1: channel['dated3']['leak']?channel['dated3']['leak']['pgaugeno1']:null,
                    pgaugeno2: channel['dated3']['leak']?channel['dated3']['leak']['pgaugeno2']:null,
                    dewelltime: channel['dated3']['leak']?channel['dated3']['leak']['dewelltime']:null,
                    circutemp: channel['dated3']['leak']?channel['dated3']['leak']['circutemp']:null,
                    mediatemp: channel['dated3']['leak']?channel['dated3']['leak']['mediatemp']:null,
                    leaktestp: channel['leaktestp'],
                    ppart: channel['dated3']['leak']?channel['dated3']['leak']['ppart']:null,
                    testmedia: channel['dated3']['leak']?channel['dated3']['leak']['testmedia']:null,
                  }
                }
              }
              fbb.controls['dated1'].enable();
              fbb.controls['dated2'].enable();
              fbb.controls['dated3'].enable();
              fbb.controls['testmedia'].enable();
              if(model['dated1'].status==2){
                fbb.controls['dated1'].disable();
                fbb.controls['testmedia'].disable();
              }
              if(model['dated2'].status==2){
                fbb.controls['dated2'].disable();
              }
              if(model['dated3'].status==2){
                fbb.controls['dated3'].disable();
              }
              this.dataModel.push(model)
              this.channelForms.push(fbb);
            }
            this.loading = false;
            this.showChannel = true;
          }else{
            this.loading = false;
            this.message.error("查询通道信息失败！请稍后重试")
          }
        })
    }
  }
  showChannel = false;
  formatInDate(control){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
    }
  }
  formateDate2(index,dateindex,type){
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(this.dataModel[index][dateindex][type].date)){
      this.dataModel[index][dateindex][type].date = new Date().getFullYear()+"-"+this.dataModel[index][dateindex][type].date;
    }else if(!yearMonthDay.test(this.dataModel[index][dateindex][type].date)){
      this.dataModel[index][dateindex][type].date = null;
    }
  }

  submitDatedInfo(index){
    let form = this.channelForms[index];
    for (const i in form.controls) {
      form.controls[ i ].markAsDirty();
      form.controls[ i ].updateValueAndValidity();
    }
    if(form.valid){
      this.testParametersService.putPressureTest({
        prodno:form.controls['prodno'].value,
        dwgno:form.controls['dwgno'].value,
        ppart:form.controls['ppart'].value,
        eppart:form.controls['eppart'].value,
        dated1:form.controls['dated1'].value,
        dated2:form.controls['dated2'].value,
        dated3:form.controls['dated3'].value,
        testmedia:form.controls['testmedia'].value.name,
        etestmedia:form.controls['testmedia'].value.ename,
        clcontent:form.controls['testmedia'].value.cl,
        user:this._storage.get("username")
      }).subscribe((res)=>{
        if(res["result"]=="success"){
          this.modalService.success({
            nzTitle:"成功！",
            nzContent: '提交开具日期信息成功！'
          })
          let prodno = this.validateForm.controls['prodno'].value;
          this.checklink()
        }
      })
    }
  }

  submitForm(index):void{
    let valid = true;
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(!this.validateForm.valid){
      valid = false;
    }
    let form = this.channelForms[index];
    for (const i in form.controls) {
      form.controls[ i ].markAsDirty();
      form.controls[ i ].updateValueAndValidity();
    }
    if(!form.valid){
      valid = false;
    }
    if(valid){
      this.testParametersService.putPressureTest({
        prodno:form.controls['prodno'].value,
        dwgno:form.controls['dwgno'].value,
        ppart:form.controls['ppart'].value,
        eppart:form.controls['eppart'].value,
        dated1:form.controls['dated1'].value,
        dated2:form.controls['dated2'].value,
        dated3:form.controls['dated3'].value,
        testmedia:form.controls['testmedia'].value.name,
        etestmedia:form.controls['testmedia'].value.ename,
        clcontent:form.controls['testmedia'].value.cl,
        user:this._storage.get("username")
      }).subscribe((res)=>{
        if(res["result"]=="success"){
          for(let i in this.dataModel[index]){
            if(this.dataModel[index][i])
              if(this.dataModel[index][i].status == 0){
                this.dataModel[index][i] = {status:0};
              }else{
                if(this.dataModel[index][i]['press'])
                  this.dataModel[index][i].press.ppart = form.controls['ppart'].value;
                if(this.dataModel[index][i]['leak'])
                  this.dataModel[index][i].leak.ppart = form.controls['ppart'].value;
              }
          }
          this.testParametersService.putpreandleak(
            {
              "prodno":form.controls['prodno'].value,
              "ppart":form.controls['ppart'].value,
              "data":this.dataModel[index]
            }).subscribe((res)=>{
            if(res['result']=="success"){
              this.modalService.success({
                nzTitle: '成功',
                nzContent: '您已提交成功！'
              });
              this.checklink();
            }
          });
        }
      },(err)=>{
        this.modalService.error({
          nzTitle: '提示',
          nzContent: '您的信息未填写完整！'
        });
      })
    }else{
      this.modalService.error({
        nzTitle: '提示',
        nzContent: '您的信息未填写完整！'
      });
    }
  }
  linkProdnoandDwgno(){
    this.testParametersService.putPressureTest({prodno:this.validateForm.controls['prodno'].value,dwgno:this.validateForm.controls['dwgno'].value,user:this._storage.get("username")}).subscribe(res=>{
      if(res['result'] == 'success'){
        this.modalService.success({
          nzTitle: '成功',
          nzContent: '连接成功！'
        });
        this.link = 2;
        this.checklink();
        this.validateForm.controls['dwgno'].disable();
      }
    })
  }
  unlinkProdnoandDwgno(){
    this.modalService.confirm({
      nzTitle  : '<i>请确认本次操作</i>',
      nzOkText    : '我确认',
      nzOkType    : 'danger',
      nzContent: '<b>你确认要取消产品编号'+this.validateForm.controls['prodno'].value+'与图号'+this.validateForm.controls['dwgno'].value+'的连接吗?此次数据将会删除之前所有与该产品编号有关的数据！</b>',
      nzOnOk   : () =>{
        this.testParametersService.unlinkProdnoandDwgno(this.validateForm.controls['prodno'].value,this.validateForm.controls['dwgno'].value).subscribe(res=>{
          if(res['result'] == "success"){
            this.link = 0;
            this.validateForm.controls['prodno'].setValue(null);
            this.validateForm.controls['dwgno'].setValue(null);
            this.validateForm.controls['dwgno'].enable();
            this.modalService.success({
              nzTitle: '成功',
              nzContent: '取消连接成功！'
            });
          }else{
            this.modalService.error({
              nzTitle: '失败',
              nzContent: '取消连接失败，您可以稍后尝试重新取消连接'
            });
          }
        })
      }
    });
  }
  displayPressRange(index,dated:string){
    if(!this.dataModel[index][dated].press.date){
      this.message.error("请先选择试压日期！");
      return;
    }
    if(this.dataModel[index][dated].press.pgaugeno1 && this.dataModel[index][dated].press.date)
      this.testParametersService.searchmeabyexi(this.dataModel[index][dated].press.pgaugeno1,this.dataModel[index][dated].press.date).subscribe(res=>{
        if(res['result'] == "success"){
          this.dataModel[index][dated].press.range = {
            max:res['max'],
            min:res['min']};
          if(this.dataModel[index][dated].press.testpress * 3 >= this.dataModel[index][dated].press.range.max &&
            this.dataModel[index][dated].press.testpress * 1.5 <= this.dataModel[index][dated].press.range.max) {
          }else{
            this.dataModel[index][dated].press.range = null;
            this.dataModel[index][dated].press.pgaugeno1 = null;
            this.message.error("压力超出量程表超出范围，请选择其它量程表！")
          }
        }else{
          this.dataModel[index][dated].press.range = null;
          this.dataModel[index][dated].press.pgaugeno1 = null;
          this.message.error("未查到压力量程表范围，请检查日期!")
        }
      })
  }
  displayPressRange2(index,dated:string){
    if(!this.dataModel[index][dated].press.date){
      this.message.error("请先选择试压日期！");
      return;
    }
    if(!this.dataModel[index][dated].press.pgaugeno1){
      this.message.error("请先选择压力表1！")
      this.dataModel[index][dated].press.pgaugeno2 = null;
    }else if(this.dataModel[index][dated].press.pgaugeno2){
      this.testParametersService.searchmeabyexi(this.dataModel[index][dated].press.pgaugeno2,this.dataModel[index][dated].press.date).subscribe(res=>{
        if(res['result'] == "success"){
          if(res['max']!=this.dataModel[index][dated].press.range['max'] || res['min']!=this.dataModel[index][dated].press.range['min']){
            this.message.error("压力量程表2量程与量程1不符,请重新选择！");
            this.dataModel[index][dated].press.pgaugeno2 = null;
          }
        }else{
          this.dataModel[index][dated].press.pgaugeno2 = null;
          this.message.error("未查到压力量程表范围，请检查量程表或日期!")
        }
      })
    }
  }
}

