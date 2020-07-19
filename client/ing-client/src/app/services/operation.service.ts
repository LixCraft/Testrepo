import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Operation, SaveOperationRequest} from "../model/operation.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  constructor(private http: HttpClient) { }

  public saveOperation(operation : SaveOperationRequest) : Observable<Operation>{
    return this.http.post<Operation>('ingbankaccount/operation/save/', operation);
  }
}
