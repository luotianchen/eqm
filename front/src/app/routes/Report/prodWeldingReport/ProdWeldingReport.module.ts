import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ProdWeldingReportComponent} from "./ProdWeldingReport.component";

@NgModule({
  declarations: [
    ProdWeldingReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ProdWeldingReportComponent
  ]
})

export class ProdWeldingReportModule {

}
