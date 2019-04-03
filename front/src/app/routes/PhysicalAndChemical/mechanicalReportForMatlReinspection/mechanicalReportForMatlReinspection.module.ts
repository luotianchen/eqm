import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MechanicalReportForMatlReinspectionComponent} from "./mechanicalReportForMatlReinspection.component";

@NgModule({
  declarations: [
    MechanicalReportForMatlReinspectionComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MechanicalReportForMatlReinspectionComponent
  ]
})

export class MechanicalReportForMatlReinspectionModule {

}
