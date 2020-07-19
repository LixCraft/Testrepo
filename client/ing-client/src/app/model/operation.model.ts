export class SaveOperationRequest {
  accountId: number;
  deposit: boolean = true;
  overdraft: boolean = false;
  amount: number = 0;
  description: string;
}

export class Operation {
  operationId: number;
  accountId: number;
  category: string;
  description: string;
  amount: number;
  date: Date;
}
