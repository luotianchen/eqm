import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {DesignQueryComponent} from './designQuery.component';

@NgModule({
  declarations: [
    DesignQueryComponent
  ],
  imports: [
    SharedModule.forRoot()
  ],
  exports: [
    DesignQueryComponent
  ]
})

export class DesignQueryModule {

}
