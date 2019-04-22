import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared.module';
import {Exception404Component} from './exception404.component';
import {Exception403Component} from "./exception403.component";

@NgModule({
  declarations: [Exception404Component,Exception403Component],
  imports: [SharedModule],
  exports: [Exception404Component,Exception403Component]
})

export class ExceptionModule {

}
