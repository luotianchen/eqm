import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WeldingRecordComponent} from "./weldingRecord.component";

@NgModule({
  declarations: [
    WeldingRecordComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    WeldingRecordComponent
  ]
})

export class WeldingRecordModule {

}
