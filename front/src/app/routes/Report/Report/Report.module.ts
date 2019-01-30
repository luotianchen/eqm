import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ReportComponent} from "./Report.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    ReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    ReportComponent
  ]
})

export class ReportModule {

}
