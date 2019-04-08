import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {LeaktestReportComponent} from "./leaktestReport.component";

@NgModule({
  declarations: [
    LeaktestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    LeaktestReportComponent
  ]
})

export class LeaktestReportModule {

}
