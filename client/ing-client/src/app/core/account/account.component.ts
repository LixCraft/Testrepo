import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AccountService } from '../../services/account.service';
import {Operation, SaveOperationRequest} from "../../model/operation.model";
import {Customer} from "../../model/customer.model";
import {ActivatedRoute} from "@angular/router";
import {AccountDto} from "../../model/account.model";
import {CustomerService} from "../../services/customer.service";
import {OperationService} from "../../services/operation.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  public operationTypeListe = {
    deposit: 'deposit',
    withdraw: 'withdraw'
  }
  public accounts : Array<AccountDto>;
  public customerId : string;
  public selectedAccountDto : AccountDto;
  public overdraft : boolean = false;
  public customer : Customer;
  public displayedColumns: string[] = ['date', 'description', 'category', 'amount'];
  public isDeposit: boolean;
  public operationType : any;
  public amountToSave : number =0;
  public descriptionToSave: string;
  constructor(
    private activeRoute : ActivatedRoute,
    private accountService : AccountService,
    private customerService : CustomerService,
    private operationService : OperationService
  ) { }

  ngOnInit() {
    this.activeRoute.params.subscribe(
      value => {
        this.customerId = value['customerId'];
        this.customerService.customer(this.customerId).subscribe(
            customer => {
              this.customer = customer;
              console.log(customer);

            }
        )
        this.accountService.listAccount(value['customerId']).subscribe(
          listAccount => {
            console.log(listAccount);
            this.accounts = listAccount;
          }
        )
      }
    )
  }

  public changeAccount(accountDto: AccountDto){
    this.selectedAccountDto = accountDto;
  }
  public getSum() : number{
    let sum : number = 0;
    this.selectedAccountDto.operations.forEach(
        item => {
          sum += item.amount;
        }
    )
    return sum;
  }

  public changeOperationTYpe(operationType :any) {
    this.operationType = operationType;
    if(operationType == this.operationTypeListe.deposit){
      this.isDeposit = true;
    } else if(operationType == this.operationTypeListe.withdraw){
      this.isDeposit = false;
    }
  }

  public saveOperation (){
    let saveOperation : SaveOperationRequest = {
      accountId: this.selectedAccountDto.account.accountId,
      deposit: this.isDeposit,
      overdraft: this.overdraft,
      amount: this.amountToSave,
      description: this.descriptionToSave
    };
    this.operationService.saveOperation(saveOperation).subscribe(
        value => {
          this.selectedAccountDto.operations = [value, ...this.selectedAccountDto.operations];
        }
    )
  }



}
