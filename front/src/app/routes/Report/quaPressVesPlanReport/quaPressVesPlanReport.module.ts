import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {QuaPressVesPlanReportComponent} from "./quaPressVesPlanReport.component";
import { PdfViewerModule } from 'ng2-pdf-viewer';

@NgModule({
  declarations: [
    QuaPressVesPlanReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    PdfViewerModule
  ],
  exports: [
    QuaPressVesPlanReportComponent
  ]
})

export class QuaPressVesPlanReportModule {

}
