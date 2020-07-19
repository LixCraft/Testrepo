import {RouterModule, Routes} from "@angular/router";
import {CustomerSelectionComponent} from "./customer-selection/customer-selection.component";
import {AccountComponent} from "./account/account.component";
import {ModuleWithProviders} from "@angular/core";

const routes : Routes= [
  {path : '', redirectTo : '/customerSelection', pathMatch: 'full'},
  {path : 'customerSelection', component: CustomerSelectionComponent},
  {path : 'account/:customerId', component: AccountComponent}
]

export const CoreRoutingModule : ModuleWithProviders = RouterModule.forChild(routes);
