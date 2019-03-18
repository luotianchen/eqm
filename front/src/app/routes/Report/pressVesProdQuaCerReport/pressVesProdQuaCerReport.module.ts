import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressVesProdQuaCerReportComponent} from "./pressVesProdQuaCerReport.component";

@NgModule({
  declarations: [
    PressVesProdQuaCerReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressVesProdQuaCerReportComponent
  ]
})

export class PressVesProdQuaCerReportModule {

}
