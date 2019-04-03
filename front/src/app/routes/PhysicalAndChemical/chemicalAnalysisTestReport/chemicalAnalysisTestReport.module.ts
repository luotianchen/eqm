import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {ChemicalAnalysisTestReportComponent} from "./chemicalAnalysisTestReport.component";

@NgModule({
  declarations: [
    ChemicalAnalysisTestReportComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ChemicalAnalysisTestReportComponent
  ]
})

export class ChemicalAnalysisTestReportModule {

}
