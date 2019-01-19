import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MeasuringInstrumentLedgerQueryComponent} from './measuringInstrumentLedgerQuery.component';

@NgModule({
  declarations: [
    MeasuringInstrumentLedgerQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MeasuringInstrumentLedgerQueryComponent
  ]
})

export class MeasuringInstrumentLedgerQueryModule {

}
