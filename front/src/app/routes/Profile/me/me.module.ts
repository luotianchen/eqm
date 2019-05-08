import {NgModule} from '@angular/core';
import {MeComponent} from './me.component';
import {SharedModule} from '../../../shared.module';

@NgModule({
  declarations: [MeComponent],
  imports: [
    SharedModule
  ],
  exports: [MeComponent]
})

export class MeModule {

}
