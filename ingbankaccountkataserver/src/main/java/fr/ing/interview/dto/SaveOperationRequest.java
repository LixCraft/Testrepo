package fr.ing.interview.dto;

import lombok.Data;

@Data
public class SaveOperationRequest {

    private Long accountId;
    private boolean deposit;
    private boolean overdraft;
    private Double amount;
    private String description;
}
