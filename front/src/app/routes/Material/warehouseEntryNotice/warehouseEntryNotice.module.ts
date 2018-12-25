import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {WarehouseEntryNoticeComponent} from "./warehouseEntryNotice.component";
import {EssenceNg2PrintModule} from "essence-ng2-print";

@NgModule({
  declarations: [
    WarehouseEntryNoticeComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    WarehouseEntryNoticeComponent
  ]
})

export class WarehouseEntryNoticeModule {

}
