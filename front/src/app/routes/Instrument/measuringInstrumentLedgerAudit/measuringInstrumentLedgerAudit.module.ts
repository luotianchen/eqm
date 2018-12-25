import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MeasuringInstrumentLedgerAuditComponent} from './measuringInstrumentLedgerAudit.component';

@NgModule({
  declarations: [
    MeasuringInstrumentLedgerAuditComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MeasuringInstrumentLedgerAuditComponent
  ]
})

export class MeasuringInstrumentLedgerAuditModule {

}
