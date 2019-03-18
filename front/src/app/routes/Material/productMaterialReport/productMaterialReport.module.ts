import {NgModule} from "@angular/core";
import {ProductMaterialReportComponent} from "./productMaterialReport.component";
import {SharedModule} from "../../../shared.module";
import {PdfViewerModule} from "ng2-pdf-viewer";

@NgModule({
  declarations: [
    ProductMaterialReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    PdfViewerModule
  ],
  exports: [
    ProductMaterialReportComponent
  ]
})

export class ProductMaterialReportModule {

}
