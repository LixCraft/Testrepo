package fr.ing.interview.mapper;

import fr.ing.interview.dto.SaveOperationRequest;
import fr.ing.interview.model.Operation;

import java.time.LocalDateTime;

public class OperationMapper {

    public static Operation map(SaveOperationRequest saveOperationRequest){
        Operation operation = new Operation();
        operation.setAccountId(saveOperationRequest.getAccountId());
        operation.setDescription(saveOperationRequest.getDescription());
        operation.setDate(LocalDateTime.now());
        operation.setAmount(saveOperationRequest.isDeposit() ? saveOperationRequest.getAmount() : (-saveOperationRequest.getAmount()));
        return operation;
    }
}
