import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {TestParametersComponent} from './testParameters.component';

@NgModule({
  declarations: [
    TestParametersComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    TestParametersComponent
  ]
})

export class TestParametersModule {

}
