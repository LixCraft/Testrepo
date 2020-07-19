package fr.ing.interview.dto;

import fr.ing.interview.model.Account;
import fr.ing.interview.model.Operation;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private Account account;
    private List<Operation> operations;
}
