import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {EssenceNg2PrintModule} from 'essence-ng2-print';
import {ConQuaInfoFbSheetComponent} from "./conQuaInfoFbSheet.component";

@NgModule({
  declarations: [
    ConQuaInfoFbSheetComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    ConQuaInfoFbSheetComponent
  ]
})

export class ConQuaInfoFbSheetModule {

}
