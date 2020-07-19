import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountComponent} from './core/account/account.component';
import {CustomerSelectionComponent} from "./core/customer-selection/customer-selection.component";

const routes: Routes = [
  {path : '', redirectTo: 'customerSelection', pathMatch: 'full'},
  {path : 'core', loadChildren: './core/core.module#CoreModule'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
