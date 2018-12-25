import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MeasuringInstrumentSettingComponent} from "./measuringInstrumentSetting.component";

@NgModule({
  declarations: [
    MeasuringInstrumentSettingComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    MeasuringInstrumentSettingComponent
  ]
})

export class MeasuringInstrumentSettingModule {

}
