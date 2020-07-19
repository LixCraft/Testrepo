import {Operation} from "./operation.model";

export class Account{
  accountId : number;
  customerId : number;
  accountNumber : string;
  typeCode : string;
  typeLabel : string;
}

export class AccountDto {
  account : Account;
  operations : Array<Operation>;
}
