import {NgModule} from '@angular/core';
import {AccessPermissionComponent} from "./accessPermission.component";
import {SharedModule} from "../../../shared.module";
import {LoginModule} from "../../login/login.module";

@NgModule({
  declarations: [AccessPermissionComponent],
  imports: [SharedModule],
  exports: [AccessPermissionComponent]
})

export class AccessPermissionModule {

}
