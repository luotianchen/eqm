import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import { NgxXLSXService } from '@notadd/ngx-xlsx';
import {ProductManufacturingParametersQueryService} from './productManufacturingParametersQuery.service';

@Component({
  selector: 'app-productManufacturingParametersQuery',
  templateUrl: 'productManufacturingParametersQuery.component.html',
  styleUrls: ['./productManufacturingParametersQuery.component.less'],
  providers: [ProductManufacturingParametersQueryService]
})
export class ProductManufacturingParametersQueryComponent implements OnInit {
  constructor(public productManufacturingParametersQueryService: ProductManufacturingParametersQueryService,private excel:NgxXLSXService,public fb:FormBuilder) {
  }
  loading = false;
  validateForm:FormGroup;
  dataSet = [];
  dataSet2 = [];
  prodnos = [];
  headers = [
    "审核人",
    "A类焊缝最大错边量",
    "A类焊缝最大棱角度",
    "下料日期",
    "B类焊缝最大错边量",
    "B类焊缝最大棱角度",
    "封头形状偏差内凹实测值",
    "外协情况",
    "提交日期",
    "设计变更",
    "设计日期",
    "资料员",
    "图号",
    "设备代码",
    "订货单位英文",
    "制造完工日期",
    "筒体内径实测值",
    "筒体长度",
    "材料复验",
    "封头成形最小厚度实测值",
    "封头成形最小厚度实测值标准值",
    "新工艺",
    "新焊接工艺评定",
    "订货单位",
    "封头形状偏差外凸实测值",
    "境外材料",
    "产品编号",
    "产品总高",
    "筒体圆度",
    "特殊材料",
    "筒体直线度",
    "材料代用",
    "冷卷筒体投料厚度实测值",
    "提交人",
    "焊缝余高（双面坡口）",
    "焊缝余高（单面坡口）"
  ]
  ngOnInit(): void {
    this.productManufacturingParametersQueryService.getprodno().subscribe(res=>{
      if(res['result'] == "success")
        this.prodnos = res['data'];
    })
    this.validateForm = this.fb.group({
      "prodno":[null]
    })
    this.searchData();
  }
  searchData(){
    this.loading = true;
    this.productManufacturingParametersQueryService.getAudit(this.validateForm.value.prodno).subscribe((res)=>{
      if(res['result']=='success'){
        this.dataSet = res['data'];
        for(let data of this.dataSet){
          data.expand = false;
        }
        this.loading = false;
      }else{
        this.loading = false;
      }
    },err=>{
      this.loading = false;
    })
  }
  parse(data){
    return JSON.parse(data)
  }
  exportExcel(){
    this.productManufacturingParametersQueryService.getAudit(this.validateForm.value.prodno).subscribe((res)=>{
      if(res['result']=="success"){
        this.excel.exportAsExcelFile(res['data'],'产品制造参数',this.headers)
      }
    })
  }
}
