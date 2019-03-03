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
  public channel = [];
  public exitnos = [];
  public dataModel = [
  ]
  public link = false;
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
      label: "管程",value:{name:"管程",ename: "TubeSide"}
    },{
      label: "管程I",value:{name:"管程I",ename: "TubeSide I"}
    },{
      label: "管程II",value:{name:"管程II",ename: "TubeSide II"}
    },{
      label: "管程III",value:{name:"管程III",ename: "TubeSide III"}
    },{
      label: "管程IV",value:{name:"管程IV",ename: "TubeSide IV"}
    },{
      label: "管程V",value:{name:"管程V",ename: "TubeSide V"}
    },{
      label: "管程VI",value:{name:"管程VI",ename: "TubeSide VI"}
    },{
      label: "管程VII",value:{name:"管程VII",ename: "TubeSide VII"}
    },{
      label: "管程VIII",value:{name:"管程VIII",ename: "TubeSide VIII"}
    },{
      label: "管程IX",value:{name:"管程VIII",ename: "TubeSide IX"}
    },{
      label: "管程X",value:{name:"管程VIII",ename: "TubeSide X"}
    },{
      label: "管程XI",value:{name:"管程VIII",ename: "TubeSide XI"}
    },{
      label: "管程XII",value:{name:"管程VIII",ename: "TubeSide XII"}
    },{
      label: "壳程",value:{name:"壳程",ename: "Shell Side"}
    },{
      label: "壳程I",value:{name:"壳程I",ename: "Shell Side I"}
    },{
      label: "壳程II",value:{name:"壳程II",ename: "Shell Side II"}
    },{
      label: "壳程III",value:{name:"壳程III",ename: "Shell Side III"}
    },{
      label: "壳程IV",value:{name:"壳程IV",ename: "Shell Side IV"}
    },{
      label: "壳程V",value:{name:"壳程V",ename: "Shell Side V"}
    },{
      label: "壳程VI",value:{name:"壳程VI",ename: "Shell Side VI"}
    },{
      label: "壳程VII",value:{name:"壳程VII",ename: "Shell Side VII"}
    },{
      label: "壳程VIII",value:{name:"壳程VIII",ename: "Shell Side VIII"}
    },{
      label: "壳程IX",value:{name:"壳程IX",ename: "Shell Side IX"}
    },{
      label: "壳程X",value:{name:"壳程X",ename: "Shell Side X"}
    },{
      label: "壳程XI",value:{name:"壳程XI",ename: "Shell Side XI"}
    },{
      label: "壳程XII",value:{name:"壳程XII",ename: "Shell Side XII"}
    },    {
      label: "I通道",value:{name:"I通道",ename: "I gallery"}
    },{
      label: "II通道",value:{name:"II通道",ename: "II gallery"}
    },{
      label: "III通道",value:{name:"III通道",ename: "III gallery"}
    },{
      label: "IV通道",value:{name:"IV通道",ename: "IVgallery"}
    },{
      label: "V通道",value:{name:"V通道",ename: "V gallery"}
    },{
      label: "VI通道",value:{name:"VI通道",ename: "VI gallery"}
    },{
      label: "VII通道",value:{name:"VII通道",ename: "VII gallery"}
    },{
      label: "VIII通道",value:{name:"VIII通道",ename: "VIII gallery"}
    },{
      label: "IX通道",value:{name:"IX通道",ename: "IX gallery"}
    },{
      label: "X通道",value:{name:"X通道",ename: "X gallery"}
    },{
      label: "XI通道",value:{name:"XI通道",ename: "XI gallery"}
    },{
      label: "XII通道",value:{name:"XII通道",ename: "XII gallery"}
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

  getChannel():void{
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.testParametersService.getchannel(this.validateForm.controls['dwgno'].value).subscribe((res)=>{
        this.channel = res["data"];
        this.channelForms = [];
        for(let data of this.channel){
          let fbb = this.fb.group({
            prodno:[this.validateForm.value.prodno, [Validators.required]],
            dwgno:[this.validateForm.value.dwgno, [Validators.required]],
            ppart:[data.name, [Validators.required]],
            eppart:[data.ename, [Validators.required]],
            dated1:[null],
            dated2:[null],
            dated3:[null],
            testmedia:[null],
            etestmedia:[null],
            clcontent:[null]
          });
          this.testParametersService.gettestpressurebyprodnoanddwgno(this.validateForm.value.prodno,this.validateForm.value.dwgno,data.name).subscribe((res)=>{
            if(res['result']=="success"){
              let item :any;
              for(let testmedia of this.testmedias){
                if(testmedia.name == res['data']['testmedia']){
                  item = testmedia;
                }
              }
              fbb.controls['testmedia'].setValue(item);
            }else if(res['result'] == "donot"){
              this.link = true;
            }
          })

          let model ={
            leaktest:false,
            dated1:{
              status:1,//0隐藏，1显示，2禁用
              "press": {
                "date": null,
                "pgaugeno1": null,
                "pgaugeno2": null,
                "dewelltime": null,
                "circutemp": null,
                "mediatemp": null,
                "testpress":null,
                "ppart":null,
                "testmedia":null
              },
              //泄漏参数
              "leak": {
                "date": null,
                "pgaugeno1": null,
                "pgaugeno2": null,
                "dewelltime": null,
                "circutemp": null,
                "mediatemp": null,
                "leaktestp": null,
                "ppart":null,
                "testmedia":null,
              }
            },
            dated2:{
              status:0,
              "press": {
                "date": null,
                "pgaugeno1": null,
                "pgaugeno2": null,
                "dewelltime": null,
                "circutemp": null,
                "mediatemp": null,
                "testpress":null,
                "ppart":null,
                "testmedia":null
              },
              //泄漏参数
              "leak": {
                "date": null,
                "pgaugeno1": null,
                "pgaugeno2": null,
                "dewelltime": null,
                "circutemp": null,
                "mediatemp": null,
                "leaktestp": null,
                "ppart":null,
                "testmedia":null,
              }
            },
            dated3:{
              status:0,
              "press": {
                "date": null,
                "pgaugeno1": null,
                "pgaugeno2": null,
                "dewelltime": null,
                "circutemp": null,
                "mediatemp": null,
                "testpress":null,
                "ppart":null,
                "testmedia":null
              },
              //泄漏参数
              "leak": {
                "date": null,
                "pgaugeno1": null,
                "pgaugeno2": null,
                "dewelltime": null,
                "circutemp": null,
                "mediatemp": null,
                "leaktestp": null,
                "ppart":null,
                "testmedia":null,
              }
            }
          };
          if (data.leaktest!="/")
            model.leaktest = true;
          else
            model.leaktest = false;
          this.testParametersService.getPressandLeak(this.validateForm.value.prodno,data.name,'dated1').subscribe((res)=>{
            if(res['result']=="success"){
              model.dated1.status = 2;
              model.dated2.status = 1;
              model.dated1.press = res['data']['press'];
              model.dated1.press['testpress'] = data.testpress;
              model.dated1.press['ppart'] = data.name;
              fbb.setControl('dated1',new FormControl({value: res['data']['press']['dated'], disabled: true}));
              if(model.leaktest){
                model.dated1.leak = res['data']['leak'];
                model.dated1.leak['leaktestp'] = data.leaktestp;
                model.dated1.leak['ppart'] = data.name;
              }
              this.testParametersService.getPressandLeak(this.validateForm.value.prodno,data.name,'dated2').subscribe((res)=>{
                if(res['result']=="success"){
                  model.dated2.status = 2;
                  model.dated3.status = 1;
                  model.dated2.press = res['data']['press'];
                  model.dated2.press['testpress'] = data.testpress;
              model.dated2.press['ppart'] = data.name;
                  fbb.setControl('dated2',new FormControl({value: res['data']['press']['dated'], disabled: true}));
          if(model.leaktest){
            model.dated2.leak = res['data']['leak'];
            model.dated2.leak['leaktestp'] = data.leaktestp;
                model.dated2.leak['ppart'] = data.name;
          }
                  this.testParametersService.getPressandLeak(this.validateForm.value.prodno,data.name,'dated2').subscribe((res)=>{
                    if(res['result']=="success") {
                      model.dated3.status = 2;
                      model.dated3.press = res['data']['press'];
                      model.dated3.press['testpress'] = data.testpress;
              model.dated3.press['ppart'] = data.name;
                      fbb.controls['dated3'].setValue(res['data']['press']['dated']);
                      fbb.setControl('dated3',new FormControl({value: res['data']['press']['dated'], disabled: true}));
                      if(model.leaktest){
                        model.dated3.leak = res['data']['leak'];
                        model.dated3.leak['leaktestp'] = data.leaktestp;
                            model.dated3.leak['ppart'] = data.name;
                      }
                    }
                    this.dataModel.push(model);
                  })
                }else{
                  this.dataModel.push(model);
                }
              })
            }else{
              this.dataModel.push(model);
            }
          })
          this.dataModel.push(model);
          this.channelForms.push(fbb)
        }
      });
    }
  }

  formatInDate(control){ //日期格式化
    let monthDay = /^([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    let yearMonthDay = /^[1-9]\d{3}-([0]?[1-9]|1[0-2])-([0]?[1-9]|[1-2][0-9]|3[0-1])$/;
    if(monthDay.test(control.value)){
      control.setValue(new Date().getFullYear()+"-"+control.value);
    }else if(!yearMonthDay.test(control.value)){
      control.setValue(null);
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
        prodno:form.value.prodno,
        dwgno:form.value.dwgno,
        ppart:form.value.ppart.name,
        eppart:form.value.ppart.ename,
        dated1:form.controls['dated1'].value,
        dated2:form.value.dated2,
        dated3:form.value.dated3,
        testmedia:form.value.testmedia.name,
        etestmedia:form.value.testmedia.ename,
        clcontent:form.value.testmedia.cl,
        user:this._storage.get("username")
      }).subscribe((res)=>{
        if(res["result"]=="success"){
          for(let i in this.dataModel[index]){
            if(this.dataModel[index][i].status == 0){
              this.dataModel[index][i] = null;
            }
          }
          this.testParametersService.putpreandleak(
            {
              "prodno":form.value.prodno,
              "data":this.dataModel[index]
            }).subscribe((res)=>{
            if(res['result']=="success"){
              this.modalService.success({
                nzTitle: '成功',
                nzContent: '您已提交成功！'
              });
            }
          });
          this.validateForm.reset();
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
    this.testParametersService.putPressureTest({prodno:this.validateForm.value.prodno,dwgno:this.validateForm.value.dwgno,user:this._storage.get("username")}).subscribe(res=>{
      if(res['result'] == 'success'){
        this.modalService.success({
          nzTitle: '成功',
          nzContent: '连接成功！'
        });
        this.link = false;
      }
    })
  }
}
