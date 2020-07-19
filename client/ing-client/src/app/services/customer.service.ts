import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SaveOperationRequest} from "../model/operation.model";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  public listCustomers() : Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>('ingbankaccount/customer/consult/');
  }
  public customer(customerId : string) : Observable<Customer>{
    return this.http.get<Customer>('ingbankaccount/customer/consult/' + customerId);
  }

}
