import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared.module';
import {MaterialSubstitutionQueryComponent} from "./materialSubstitutionQuery.component";
import {EssenceNg2PrintModule} from "essence-ng2-print";

@NgModule({
  declarations: [
    MaterialSubstitutionQueryComponent
  ],
  imports: [
    SharedModule.forRoot(),
    EssenceNg2PrintModule
  ],
  exports: [
    MaterialSubstitutionQueryComponent
  ]
})

export class MaterialSubstitutionQueryModule {

}
