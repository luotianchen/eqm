import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProdManuChangeReportComponent} from "./prodManuChangeReport.component";

@NgModule({
  declarations: [
    ProdManuChangeReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProdManuChangeReportComponent
  ]
})

export class ProdManuChangeReportModule {

}
