import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MeasuringInstrumentLedgerComponent} from "./measuringInstrumentLedger.component";

@NgModule({
  declarations: [
    MeasuringInstrumentLedgerComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MeasuringInstrumentLedgerComponent
  ]
})

export class MeasuringInstrumentLedgerModule {

}
