import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MeasuringInstrumentLedgerQueryComponent} from './measuringInstrumentLedgerQuery.component';
import {NgxXLSXModule} from "@notadd/ngx-xlsx";

@NgModule({
  declarations: [
    MeasuringInstrumentLedgerQueryComponent
  ],
  imports: [
    SharedModule.forRoot(),
    NgxXLSXModule
  ],
  exports: [
    MeasuringInstrumentLedgerQueryComponent
  ]
})

export class MeasuringInstrumentLedgerQueryModule {

}
