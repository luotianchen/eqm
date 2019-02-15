import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {CleaningInsReportComponent} from "./cleaningInsReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    CleaningInsReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    CleaningInsReportComponent
  ]
})

export class CleaningInsReportModule {

}
