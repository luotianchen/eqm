import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressVesProdQuaCerReportComponent} from "./pressVesProdQuaCerReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    PressVesProdQuaCerReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    PressVesProdQuaCerReportComponent
  ]
})

export class PressVesProdQuaCerReportModule {

}
