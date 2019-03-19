import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MeasuringInstrumentSettingComponent} from "./measuringInstrumentSetting.component";
import {AuthModule} from "../../../components/auth/auth.module";

@NgModule({
  declarations: [
    MeasuringInstrumentSettingComponent
  ],
  imports: [
    SharedModule.forRoot(),
    AuthModule
  ],
  exports: [
    MeasuringInstrumentSettingComponent
  ]
})

export class MeasuringInstrumentSettingModule {

}
