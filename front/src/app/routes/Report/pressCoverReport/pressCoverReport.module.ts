import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressCoverReportComponent} from "./pressCoverReport.component";
import {PdfViewerModule} from "ng2-pdf-viewer";

@NgModule({
  declarations: [
    PressCoverReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    PdfViewerModule
  ],
  exports: [
    PressCoverReportComponent
  ]
})

export class PressCoverReportModule {

}
