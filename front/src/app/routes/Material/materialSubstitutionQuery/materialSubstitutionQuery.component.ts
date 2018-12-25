import {Component, OnInit} from '@angular/core';
import {FormBuilder,  FormGroup, Validators} from "@angular/forms";
import {MaterialSubstitutionQueryService} from "./materialSubstitutionQuery.service";

@Component({
  selector: 'app-materialSubstitutionQuery',
  templateUrl: 'materialSubstitutionQuery.component.html',
  styleUrls: ['./materialSubstitutionQuery.component.less'],
  providers: [MaterialSubstitutionQueryService]
})
export class MaterialSubstitutionQueryComponent implements OnInit {
  validateForm: FormGroup;
  private pageindex = 1;
  private pagesize = 25;
  private total = 0;
  private loading = true;
  prodnos = [];
  private dataset = [];
  private status = false;
  private reportData = {
    prodname:"",
    prodno:"",
    dwgno:"",
    date:"",
    why:"",
    user:"",
    index1:"",
    name1:"",
    designmatl1:"",
    designspec1:"",
    substitutematl1:"",
    substitutespec1:"",
    type1:"",
    index2:"",
    name2:"",
    designmatl2:"",
    designspec2:"",
    substitutematl2:"",
    substitutespec2:"",
    type2:"",
    index3:"",
    name3:"",
    designmatl3:"",
    designspec3:"",
    substitutematl3:"",
    substitutespec3:"",
    type3:"",
    index4:"",
    name4:"",
    designmatl4:"",
    designspec4:"",
    substitutematl4:"",
    substitutespec4:"",
    type4:"",
    index5:"",
    name5:"",
    designmatl5:"",
    designspec5:"",
    substitutematl5:"",
    substitutespec5:"",
    type5:"",
    design_note:"",
    matl_note:"",
    welding_note:"",
    process_note:"",
    inspection_note:"",
    b_note:"",
    c_note:"",
    design_username:"",
    matl_username:"",
    welding_username:"",
    process_username:"",
    inspection_username:"",
    b_username:"",
    c_username:"",
    design_date:"",
    matl_date:"",
    welding_date:"",
    process_date:"",
    inspection_date:"",
    b_date:"",
    c_date:"",
  };
  constructor(private fb: FormBuilder, private materialSubstitutionQueryService: MaterialSubstitutionQueryService) {
  }
  resetForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].reset();
    }
  }
  ngOnInit(): void {
    this.materialSubstitutionQueryService.getprodno().subscribe((res) => {
      if (res["result"] == "success") {
        this.prodnos = res['data'];
      }
    });
    this.validateForm = this.validateForm = this.fb.group({
      "prodno":[null]
    })
    this.searchData();
  }

  searchData(reset: boolean = false): void {
    if (reset) {
      this.pageindex = 1;
    }
    this.loading = true;
    this.materialSubstitutionQueryService.searchallmaterial(this.pageindex,this.pagesize,this.validateForm.value.prodno).subscribe((res)=>{
      if(res["result"]=="success"){
        this.total = res["total"];
        this.dataset = res["data"];
        this.loading = false;
      }
    })
  }
  printCSS: string[];
  printStyle: string;
  printBtnBoolean = true;
  printComplete() {
    this.printBtnBoolean = true;
  }
  beforePrint() {
    this.printBtnBoolean = false;
  }
  setUrlParam(data){
    this.reportData.prodno = data.prodno;
    this.reportData.date = data.date;
    this.reportData.why = data.why;
    this.materialSubstitutionQueryService.getSignImage(data.user).subscribe((res)=>{
      if(res["result"]=="success")
        this.reportData.user = res['url'];
    });
    this.materialSubstitutionQueryService.getByProdno(data.prodno).subscribe((res)=>{
      if(res["result"]=="success"){
        this.reportData.prodname = res["prodname"];
        this.reportData.dwgno = res["dwgno"];
      }
    });
    this.materialSubstitutionQueryService.getSubstitutionByAudit(data.audit).subscribe((res)=>{
      if(res["result"]=="success"){
        for(let i =0;i<res["data"].length;i++){
          this.reportData["index"+(i+1)] = i+1;
          this.reportData["name"+(i+1)] = res["data"][i]["name"];
          this.reportData["designmatl"+(i+1)] = res["data"][i]["designmatl"];
          this.reportData["designspec"+(i+1)] = res["data"][i]["designspec"];
          this.reportData["substitutematl"+(i+1)] = res["data"][i]["substitutematl"];
          this.reportData["substitutespec"+(i+1)] = res["data"][i]["substitutespec"];
          this.reportData["type"+(i+1)] = res["data"][i]["type"];
          this.reportData["code"] = res["data"][i]["code"];
        }
        this.reportData.design_note = res["data"][0]["design_note"];
        this.reportData.design_date = res["data"][0]["design_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["design_username"]).subscribe((res)=>{
          if(res["result"]=="success")
            this.reportData.design_username = res['url'];
        },(err)=>{
          this.reportData.design_username = null;
        });
        this.reportData.matl_note = res["data"][0]["matl_note"];
        this.reportData.matl_date = res["data"][0]["matl_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["matl_username"]).subscribe((res)=>{
          if(res["result"]=="success")
            this.reportData.matl_username = res['url'];
        },(err)=>{
          this.reportData.matl_username = null;
        });
        this.reportData.welding_note = res["data"][0]["welding_note"];
        this.reportData.welding_date = res["data"][0]["welding_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["welding_username"]).subscribe((res)=>{
          if(res["result"]=="success")
            this.reportData.welding_username = res['url'];
        },(err)=>{
          this.reportData.welding_username = null;
        });
        this.reportData.process_note = res["data"][0]["process_note"];
        this.reportData.process_date = res["data"][0]["process_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["process_username"]).subscribe((res)=>{
          if(res["result"]=="success")
            this.reportData.process_username = res['url'];
        },(err)=>{
          this.reportData.process_username = null;
        });
        this.reportData.inspection_note = res["data"][0]["inspection_note"];
        this.reportData.inspection_date = res["data"][0]["inspection_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["inspection_username"]).subscribe((res)=>{
          if(res["result"]=="success")
            this.reportData.inspection_username = res['url'];
        },(err)=>{
          this.reportData.inspection_username = null;
        });
        this.reportData.b_note = res["data"][0]["b_note"];
        this.reportData.b_date = res["data"][0]["b_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["b_username"]).subscribe((res)=>{
          if(res["result"]=="success"){
            this.reportData.b_username = res['url'];
          }
        },(err)=>{
          this.reportData.b_username = null;
        });
        this.reportData.c_note = res["data"][0]["c_note"];
        this.reportData.c_date = res["data"][0]["c_date"];
        this.materialSubstitutionQueryService.getSignImage(res["data"][0]["c_username"]).subscribe((res)=>{
          if(res["result"]=="success"){
            this.reportData.c_username = res['url'];
          }
        },(err)=>{
          this.reportData.c_username = null;
        });
        console.log(this.reportData);
        this.status = true;
      }
    });
  }
}
