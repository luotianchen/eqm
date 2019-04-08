import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {BarometricReportComponent} from "./barometricReport.component";

@NgModule({
  declarations: [
    BarometricReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    BarometricReportComponent
  ]
})

export class BarometricReportModule {

}
