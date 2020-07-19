import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../model/customer.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customer-selection',
  templateUrl: './customer-selection.component.html',
  styleUrls: ['./customer-selection.component.css']
})
export class CustomerSelectionComponent implements OnInit {

  public customers : Array<Customer>;
  public selectedCustomer : Customer;
  constructor(
    private customerService : CustomerService,
    private router : Router
  ) { }

  ngOnInit() {
    this.customerService.listCustomers().subscribe(
      value => {
        this.customers = value;
      }
    )
  }

  public changeCustomer(customer : Customer){
    this.selectedCustomer = customer;
  }

  public valider(){
    this.router.navigate(['/account/' + this.selectedCustomer.customerId])
  }
}
