import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {SettingComponent} from "./setting.component";

@NgModule({
  declarations: [
    SettingComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    SettingComponent
  ]
})

export class SettingModule {

}
