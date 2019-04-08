import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService,NzModalRef, NzModalService} from "ng-zorro-antd";
import {SessionStorageService} from "../../../core/storage/storage.service";
import {ProductTestBoardCommissionReportService} from "./productTestBoardCommissionReport.service";

@Component({
  selector: 'app-productTestBoardReportCommission',
  templateUrl: 'productTestBoardCommissionReport.component.html',
  styleUrls: ['./productTestBoardCommissionReport.component.less'],
  providers: [ProductTestBoardCommissionReportService]
})
export class ProductTestBoardCommissionReportComponent implements OnInit {

  constructor(private productTestBoardCommissionReportService: ProductTestBoardCommissionReportService,private fb:FormBuilder,private message:NzMessageService,private modalService: NzModalService, private _storage: SessionStorageService) {
  }

  public prodnos = [];
  public specimennos = [];
  public departments = [];
  public designations = [];
  validateForm: FormGroup;
  ngOnInit(): void {
    this.productTestBoardCommissionReportService.getprodno().subscribe(res=>{
      if(res['result'] == "success")
        this.prodnos = res['data'];
    })
    this.productTestBoardCommissionReportService.getDepartments().subscribe(res=>{
      if(res['result'] == "success"){
        this.departments = res['data'];
      }
    })
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null, [Validators.required]],
      "specimenno":[null, [Validators.required]],
      "date":[null, [Validators.required]],
      "department":[null, [Validators.required]]
    });
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
  setSpecimennoAndSpec(){
    this.specimennos = [];
    this.designations = []
    this.productTestBoardCommissionReportService.getAudit(this.validateForm.value.prodno).subscribe(res=>{
      if(res['result'] == "success"){
        for(let item of res['data']){
          if(this.specimennos.indexOf(item.specimenno)==-1)
            this.specimennos.push(item.specimenno);
        }
      }
    })
  }
  public loading = false;
  status = false;
  submitForm(): void {
    // tslint:disable-next-line:no-any
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
      this.validateForm.controls[ i ].updateValueAndValidity();
    }
    if(this.validateForm.valid){
      this.loading = true;
      const formData = new FormData();
      formData.append('prodno', this.validateForm.value.prodno);
      formData.append('specimenno', this.validateForm.value.specimenno);
      formData.append('date', this.validateForm.value.date);
      formData.append('department', this.validateForm.value.department);
      this.productTestBoardCommissionReportService.getReport(formData).subscribe((res: ArrayBuffer)=>{
        let blob = new Blob([res]);
        let objectUrl = URL.createObjectURL(blob);
        let a = document.createElement('a');
        document.body.appendChild(a);
        let date = new Date();
        a.setAttribute('style', 'display:none');
        a.setAttribute('href', objectUrl);
        a.setAttribute('download', this.validateForm.value.prodno+"+理化试验委托单"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+".xlsx");
        a.click();
        URL.revokeObjectURL(objectUrl);
        this.loading = false;
      },err=>{
        this.loading = false;
        this.message.error("出现异常，请稍后重试！")
      })
    }
  }
}
