import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountComponent } from './account/account.component';
import { CustomerSelectionComponent } from './customer-selection/customer-selection.component';
import {CoreRoutingModule} from "./core.routing";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatTableModule} from "@angular/material/table";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatIconModule} from "@angular/material/icon";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [AccountComponent, CustomerSelectionComponent],
  imports: [
    CommonModule,
    CoreRoutingModule,
    MatFormFieldModule,
    MatSelectModule,
    MatTableModule,
    MatButtonToggleModule,
    MatIconModule,
    FormsModule
  ]
})
export class CoreModule { }
