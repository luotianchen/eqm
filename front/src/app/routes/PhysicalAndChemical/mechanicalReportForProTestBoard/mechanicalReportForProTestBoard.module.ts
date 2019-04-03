import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MechanicalReportForProTestBoardComponent} from "./mechanicalReportForProTestBoard.component";

@NgModule({
  declarations: [
    MechanicalReportForProTestBoardComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MechanicalReportForProTestBoardComponent
  ]
})

export class MechanicalReportForProTestBoardModule {

}
