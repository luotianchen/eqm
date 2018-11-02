import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared.module';
import {Exception404Component} from './exception404.component';

@NgModule({
  declarations: [Exception404Component],
  imports: [SharedModule],
  exports: [Exception404Component]
})

export class ExceptionModule {

}
