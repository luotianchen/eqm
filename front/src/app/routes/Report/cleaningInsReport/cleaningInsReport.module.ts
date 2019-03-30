import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {CleaningInsReportComponent} from "./cleaningInsReport.component";

@NgModule({
  declarations: [
    CleaningInsReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    CleaningInsReportComponent
  ]
})

export class CleaningInsReportModule {

}
