import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProductTestBoardCommissionReportComponent} from "./productTestBoardCommissionReport.component";

@NgModule({
  declarations: [
    ProductTestBoardCommissionReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProductTestBoardCommissionReportComponent
  ]
})

export class ProductTestBoardCommissionReportModule {

}
