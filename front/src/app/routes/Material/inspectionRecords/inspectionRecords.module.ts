import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {InspectionRecordsComponent} from "./inspectionRecords.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    InspectionRecordsComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    InspectionRecordsComponent
  ]
})

export class InspectionRecordsModule {

}
