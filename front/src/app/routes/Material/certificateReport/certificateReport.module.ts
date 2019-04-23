import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {CertificateReportComponent} from "./certificateReport.component";
import {PdfViewerModule} from "ng2-pdf-viewer";

@NgModule({
  declarations: [
    CertificateReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    PdfViewerModule
  ],
  exports: [
    CertificateReportComponent
  ]
})

export class CertificateReportModule {

}
