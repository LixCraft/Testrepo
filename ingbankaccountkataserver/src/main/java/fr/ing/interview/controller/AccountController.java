package fr.ing.interview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ing.interview.dto.AccountDto;
import fr.ing.interview.model.Account;
import fr.ing.interview.model.Customer;
import fr.ing.interview.repository.AccountRepository;
import fr.ing.interview.repository.CustomerRepository;
import fr.ing.interview.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/consult")
    public List<Account> accountAllList() {
        return accountRepository.findAll();
    }

    @GetMapping("/consult/customer/{customerId}")
    public List<AccountDto> accountList(@PathVariable(value = "customerId") Long customerId) {
        List<AccountDto> accountDtos = new ArrayList<>();
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if(!CollectionUtils.isEmpty(accounts)){
            for(Account acc : accounts){
                AccountDto accountDto = new AccountDto();
                accountDto.setAccount(acc);
                accountDto.setOperations(operationRepository.findByAccountIdOrderByDateDesc(acc.getAccountId()));
                accountDtos.add(accountDto);
            }
        }
        return accountDtos;
    }
}
