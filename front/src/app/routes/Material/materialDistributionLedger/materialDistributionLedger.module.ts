import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialDistributionLedgerComponent} from "./materialDistributionLedger.component";
import {EssenceNg2PrintModule} from 'essence-ng2-print';

@NgModule({
  declarations: [
    MaterialDistributionLedgerComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    MaterialDistributionLedgerComponent
  ]
})

export class MaterialDistributionLedgerModule {

}
