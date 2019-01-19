import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {DesignInputComponent} from "./designInput.component";

@NgModule({
  declarations: [
    DesignInputComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    DesignInputComponent
  ]
})

export class DesignInputModule {

}
