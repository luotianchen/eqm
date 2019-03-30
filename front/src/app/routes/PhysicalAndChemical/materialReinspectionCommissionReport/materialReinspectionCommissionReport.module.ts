import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialReinspectionCommissionReportComponent} from "./materialReinspectionCommissionReport.component";

@NgModule({
  declarations: [
    MaterialReinspectionCommissionReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MaterialReinspectionCommissionReportComponent
  ]
})

export class MaterialReinspectionCommissionReportModule {

}
