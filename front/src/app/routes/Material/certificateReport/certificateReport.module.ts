import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {CertificateReportComponent} from "./certificateReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';
import {QRCodeModule} from "angularx-qrcode";

@NgModule({
  declarations: [
    CertificateReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule,
    QRCodeModule
  ],
  exports: [
    CertificateReportComponent
  ]
})

export class CertificateReportModule {

}
