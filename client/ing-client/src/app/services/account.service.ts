import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {SaveOperationRequest} from "../model/operation.model";
import {errorHandler} from "@angular/platform-browser/src/browser";
import {AccountDto} from "../model/account.model";


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  public listAccount(customerId : string) : Observable<Array<AccountDto>>{
    return this.http.get<Array<AccountDto>>('ingbankaccount/account/consult/customer/' + customerId);
  }
}
