import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {VacuumInspectionReportComponent} from "./vacuumInspectionReport.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    VacuumInspectionReportComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    VacuumInspectionReportComponent
  ]
})

export class VacuumInspectionReportModule {

}
