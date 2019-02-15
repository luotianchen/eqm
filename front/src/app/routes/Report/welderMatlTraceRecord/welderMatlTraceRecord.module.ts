import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WelderMatlTraceRecordComponent} from "./welderMatlTraceRecord.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    WelderMatlTraceRecordComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    WelderMatlTraceRecordComponent
  ]
})

export class WelderMatlTraceRecordModule {

}
