import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProductManufacturingParametersService} from "./productManufacturingParameters.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";

@Component({
  selector: 'app-productManufacturingParameters',
  templateUrl: 'productManufacturingParameters.component.html',
  styleUrls: ['./productManufacturingParameters.component.less'],
  providers: [ProductManufacturingParametersService]
})
export class ProductManufacturingParametersComponent implements OnInit {
  public prodnos:any;
  validateForm: FormGroup;
  validateForm2: FormGroup;
  orderunitValidateForm: FormGroup;
  dataSet = [];
  orderunits = [];
  users = [];
  types = [
    {
      name:"单层",ename:"Sigle-wall"
    },{
      name:"双层",ename:"Double-wall"
    }
  ];
  dataStand = {
    "aweldmaxangul":[],
    "bweldmaxangul":[],
    "aweldmaxalign":[],
    "bweldmaxalign":[],
    "weldreinfs":[],
    "weldreinfd":[],
    "proheight":0,//产品总高标准值
    "innerdia":[],//容器内径
    "roundness":[],//筒体圆度
    "length":0,//筒体长度
    "straightness":[],//筒体直线
    "outward":[],//封头外凸
    "concave":[]//封头内凸
  };
  dataDetail = {
    "aweldmaxangul":[],
    "bweldmaxangul":[],
    "aweldmaxalign":[],
    "bweldmaxalign":[],
    "weldreinfs":[],
    "weldreinfd":[],
    "innerdia":[],//容器内径
    "roundness":[],//筒体圆度
    "outward":[],//封头外凸
    "concave":[]//封头内凸
  }
  //下面为产品制造参数2数据
  dataStand2 = {
    "aweldmaxangul":null,
    "bweldmaxangul":null,
    "aweldmaxalign":null,
    "bweldmaxalign":null,
    "weldreinfs":null,
    "weldreinfd":null,
    "proheight":null,
    "innerdia":null,
    "roundness":null,
    "length":null,
    "straightness":null,
    "outward":null,
    "concave":null
  }

  ngOnInit(): void {
    this.productManufacturingParametersService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.productManufacturingParametersService.getOrderunit().then((res:any)=>{
      if(res['result']=="success"){
        this.orderunits = res['data'];
      }
    });
    this.productManufacturingParametersService.getUsers().subscribe(res=>{
      if(res['result']=="success"){
        this.users = res['data'];
      }
    });

    this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "orderunit":[null],
      "ecode":[null, [Validators.required]],
      "dealter":["无", [Validators.required]],
      "submatl":["无", [Validators.required]],
      "docum":[this._storage.get('name'), [Validators.required]],
      "dedate":[null, [Validators.required]],
      "blankdate":[null, [Validators.required]],
      "matlretest":["无", [Validators.required]],
      "specmatl":["无", [Validators.required]],
      "overmatl":["无", [Validators.required]],
      "nwpq":["无", [Validators.required]],
      "nprocess":["无", [Validators.required]],
      "copsitu":["无", [Validators.required]],
      "exworkdate":[null, [Validators.required]],
      "type":[null, [Validators.required]],
      "proheight":[null, [Validators.required]],
      "length":[null, [Validators.required]],
      "thick":[null, [Validators.required]],
      "minthickstand":[null, [Validators.required]],
      "minthick":[null, [Validators.required]],
      straightness:[null, [Validators.required]]
    });
    this.validateForm2 = this.fb.group({
      "prodno":[null, [Validators.required]],
      "dwgno":[null, [Validators.required]],
      "aweldmaxangul":[null, [Validators.required]],
      "bweldmaxangul":[null, [Validators.required]],
      "aweldmaxalign":[null, [Validators.required]],
      "bweldmaxalign":[null, [Validators.required]],
      "weldreinfs":[null, [Validators.required]],
      "weldreinfd":[null, [Validators.required]],
      innerdia:[null, [Validators.required]],
      roundness:[null, [Validators.required]],
      "proheight":[null, [Validators.required]],
      "length":[null, [Validators.required]],
      "thick":[null, [Validators.required]],
      "minthickstand":[null, [Validators.required]],
      "minthick":[null, [Validators.required]],
      straightness:[null, [Validators.required]],
      outward:[null, [Validators.required]],
      concave:[null, [Validators.required]],
      "exworkdate":[null, [Validators.required]]
    });
    this.orderunitValidateForm = this.fb.group({
      "orderunit":[null, [Validators.required]],
      "orderunitename":[null, [Validators.required]]
    });
  }

  searchData(): void {
    if(this.validateForm.value.prodno!=null && this.validateForm.value.prodno!=""){
      this.productManufacturingParametersService.getdistribute(this.validateForm.controls['prodno'].value).then((res) => {
        if(res['result']=="success"){
          this.validateForm.controls['dwgno'].setValue(res['dwgno']);
          this.productManufacturingParametersService.getDataStand(this.validateForm.value.dwgno).then((res) => {
            if(res['result']=="success") {
              this.dataDetail = res['data'];
              this.dataStand = res['data'];
            }
          })
          this.productManufacturingParametersService.getbydwgno(this.validateForm.value.dwgno).subscribe(res=>{
            if(res['result'] == "success"){
              this.validateForm.controls['dedate'].setValue(res['data']['designdate']);
            }
          })
        }
      });
    }
  }
  constructor(public productManufacturingParametersService: ProductManufacturingParametersService,public fb:FormBuilder,public message:NzMessageService,public modalService: NzModalService, public _storage: SessionStorageService) {
  }

  submitForm(){
    for(const i in this.validateForm.controls){
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    for(let i in this.validateForm.controls){
      if(this.validateForm.controls[i].valid == false)
        console.log(i)
    }
    if(this.validateForm.valid){
      this.productManufacturingParametersService.putManufacturing({
        "prodno":this.validateForm.value.prodno,
        "dwgno":this.validateForm.value.dwgno,
        "orderunit":this.validateForm.value.orderunit.name,
        "eorderunit":this.validateForm.value.orderunit.ename,
        "ecode":this.validateForm.value.ecode,
        "dealter":this.validateForm.value.dealter,
        "submatl":this.validateForm.value.submatl,
        "docum":this.validateForm.value.docum,
        "dedate":this.validateForm.value.dedate,
        "blankdate":this.validateForm.value.blankdate,
        "matlretest":this.validateForm.value.matlretest,
        "specmatl":this.validateForm.value.specmatl,
        "overmatl":this.validateForm.value.overmatl,
        "nwpq":this.validateForm.value.nwpq,
        "nprocess":this.validateForm.value.nprocess,
        "copsitu":this.validateForm.value.copsitu,
        "exworkdate":this.validateForm.value.exworkdate,
        "aweldmaxangul":JSON.stringify(this.dataDetail.aweldmaxangul),
        "bweldmaxangul":JSON.stringify(this.dataDetail.bweldmaxangul),
        "aweldmaxalign":JSON.stringify(this.dataDetail.aweldmaxalign),
        "bweldmaxalign":JSON.stringify(this.dataDetail.bweldmaxalign),
        "weldreinfs":JSON.stringify(this.dataDetail.weldreinfs),
        "weldreinfd":JSON.stringify(this.dataDetail.weldreinfd),
        "user":this._storage.get("username"),
        "type":this.validateForm.value.type.name,
        "etype":this.validateForm.value.type.ename,
        "proheight":this.validateForm.value.proheight,
        "innerdia":JSON.stringify(this.dataDetail.innerdia),
        "roundness":JSON.stringify(this.dataDetail.roundness),
        "length":this.validateForm.value.length,
        "straightness":this.validateForm.value.straightness,
        "thick":this.validateForm.value.thick,
        "minthickstand":this.validateForm.value.minthickstand,
        "minthick":this.validateForm.value.minthick,
        "outward":JSON.stringify(this.dataDetail.outward),
        "concave":JSON.stringify(this.dataDetail.concave)
      }).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          this.validateForm.reset();
          this.dataStand = {
            "aweldmaxangul":[],
            "bweldmaxangul":[],
            "aweldmaxalign":[],
            "bweldmaxalign":[],
            "weldreinfs":[],
            "weldreinfd":[],
            "proheight":0,//产品总高标准值
            "innerdia":[],//容器内径
            "roundness":[],//筒体圆度
            "length":0,//筒体长度
            "straightness":[],//筒体直线
            "outward":[],//封头外凸
            "concave":[]//封头内凸
          }
          this.dataDetail = {
            "aweldmaxangul":[],
            "bweldmaxangul":[],
            "aweldmaxalign":[],
            "bweldmaxalign":[],
            "weldreinfs":[],
            "weldreinfd":[],
            "innerdia":[],//容器内径
            "roundness":[],//筒体圆度
            "outward":[],//封头外凸
            "concave":[]//封头内凸
          }
        }
      })
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
    if(which == "orderunit"){
      for (const i in this.orderunitValidateForm.controls) {
        this.orderunitValidateForm.controls[ i ].markAsDirty();
        this.orderunitValidateForm.controls[ i ].updateValueAndValidity();
      }
      if (this.orderunitValidateForm.valid) {
        this.productManufacturingParametersService.addOrderunit(this.orderunitValidateForm.value.orderunit,this.orderunitValidateForm.value.orderunitename).subscribe(res => {
          if (res['result'] == "success") {
            let modal = this.modalService.success({
              nzTitle: '添加成功',
              nzContent: '已成功添加一条记录！'
            });
            this.destroyTplModal();
            this.productManufacturingParametersService.getOrderunit().then((res)=>{
              if(res['result']=="success"){
                this.orderunits = res['data'];
              }
            })
          }else{
            this.message.error("添加失败，请稍后重试！");
            this.destroyTplModal();
          }
        });
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
    if(this.validateForm.value.dedate!=null && this.validateForm.value.blankdate!=null){
      let detime = this.validateForm.value.dedate.split("-");
      let blanktime = this.validateForm.value.blankdate.split("-");
      if(parseInt(blanktime[0])<parseInt(detime[0])){
        this.validateForm.controls['blankdate'].setValue(null);
      } else if(parseInt(blanktime[0]) == parseInt(detime[0]) && parseInt(blanktime[1])<parseInt(detime[1])){
        this.validateForm.controls['blankdate'].setValue(null);
      } else if(parseInt(blanktime[1]) == parseInt(detime[1]) && parseInt(blanktime[2])<parseInt(detime[2])){
        this.validateForm.controls['blankdate'].setValue(null);
      }
    }
    if(this.validateForm.value.exworkdate!=null && this.validateForm.value.blankdate!=null){
      let exworkdate = this.validateForm.value.exworkdate.split("-");
      let blanktime = this.validateForm.value.blankdate.split("-");
      if(parseInt(blanktime[0])>parseInt(exworkdate[0])){
        this.validateForm.controls['exworkdate'].setValue(null);
      } else if(parseInt(blanktime[0]) == parseInt(exworkdate[0]) && parseInt(blanktime[1])>parseInt(exworkdate[1])){
        this.validateForm.controls['exworkdate'].setValue(null);
      } else if(parseInt(blanktime[1]) == parseInt(exworkdate[1]) && parseInt(blanktime[2])>parseInt(exworkdate[2])){
        this.validateForm.controls['exworkdate'].setValue(null);
      }
    }
  }
  dwgnos = [];
  searchData2(): void {
    if(this.validateForm2.value.prodno!=null && this.validateForm2.value.prodno!=""){
      this.productManufacturingParametersService.getdwgno1and2(this.validateForm2.value.prodno).subscribe(res=>{
        if(res['result']=="success"){
          if(res['data']['dwgno1'])
            this.dwgnos.push(res['data']['dwgno1']);
          if(res['data']['dwgno2'])
            this.dwgnos.push(res['data']['dwgno2']);
        }
      })
    }
    this.productManufacturingParametersService.getDataStand2(this.validateForm2.value.dwgno).then((res) => {
      if(res['result']=="success") {
        this.dataStand2 = res['data'];
      }
    })
  }


  submitForm2(){
    for(const i in this.validateForm2.controls){
      this.validateForm2.controls[ i ].markAsDirty();
      this.validateForm2.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm2.valid){
      this.productManufacturingParametersService.putManufacturing2({
        "prodno":this.validateForm2.value.prodno,
        "dwgno":this.validateForm2.value.dwgno,
        "aweldmaxangul":this.validateForm2.value.aweldmaxangul,
        "bweldmaxangul":this.validateForm2.value.bweldmaxangul,
        "aweldmaxalign":this.validateForm2.value.aweldmaxalign,
        "bweldmaxalign":this.validateForm2.value.bweldmaxalign,
        "weldreinfs":this.validateForm2.value.weldreinfs,
        "weldreinfd":this.validateForm2.value.weldreinfd,
        "proheight":this.validateForm2.value.proheight,
        "innerdia":this.validateForm2.value.innerdia,
        "roundness":this.validateForm2.value.roundness,
        "length":this.validateForm2.value.length,
        "straightness":this.validateForm2.value.straightness,
        "thick":this.validateForm2.value.thick,
        "minthickstand":this.validateForm2.value.minthickstand,
        "minthick":this.validateForm2.value.minthick,
        "outward":this.validateForm2.value.outward,
        "concave":this.validateForm2.value.concave,
        "exworkdate":this.validateForm2.value.exworkdate,
        "user":this._storage.get("username")
      }).subscribe((res)=>{
        if(res['result']=="success"){
          this.modalService.success({
            nzTitle: '成功',
            nzContent: '您已提交成功！'
          });
          this.validateForm2.reset();
          this.dataStand2 = {
            "aweldmaxangul":null,
            "bweldmaxangul":null,
            "aweldmaxalign":null,
            "bweldmaxalign":null,
            "weldreinfs":null,
            "weldreinfd":null,
            "proheight":null,
            "innerdia":null,
            "roundness":null,
            "length":null,
            "straightness":null,
            "outward":null,
            "concave":null
          }
        }
      })
    }
  }

}
