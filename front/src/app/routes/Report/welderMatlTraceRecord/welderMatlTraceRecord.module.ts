import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WelderMatlTraceRecordComponent} from "./welderMatlTraceRecord.component";

@NgModule({
  declarations: [
    WelderMatlTraceRecordComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WelderMatlTraceRecordComponent
  ]
})

export class WelderMatlTraceRecordModule {

}
