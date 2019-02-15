import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressCoverReportComponent} from "./pressCoverReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    PressCoverReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    PressCoverReportComponent
  ]
})

export class PressCoverReportModule {

}
