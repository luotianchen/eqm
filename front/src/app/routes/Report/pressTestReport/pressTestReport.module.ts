import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {PressTestReportComponent} from "./pressTestReport.component";
import {PdfViewerModule} from "ng2-pdf-viewer";

@NgModule({
  declarations: [
    PressTestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    PressTestReportComponent
  ]
})

export class pressTestReportModule {

}
