package fr.ing.interview.controller;

import fr.ing.interview.dto.SaveOperationRequest;
import fr.ing.interview.exception.IngBankException;
import fr.ing.interview.facade.OperationFacade;
import fr.ing.interview.mapper.OperationMapper;
import fr.ing.interview.model.Operation;
import fr.ing.interview.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/consult")
    public List<Operation> operationAllList() {
        return operationRepository.findAll();
    }

    /**
     * Consult operations related to the accountId
     * @param accountId
     * @return
     */
    @GetMapping("/consult/account/{accountId}")
    public List<Operation> operationAllList(@PathVariable("accountId") Long accountId) {
        return operationRepository.findByAccountIdOrderByDateDesc(accountId);
    }

    @PostMapping("/save")
    public Operation saveOperation(@RequestBody SaveOperationRequest saveOperationRequest){
        try {
            Operation savedOperation = new Operation();
            if(OperationFacade.checkSaveOperationParameter(saveOperationRequest)){
                List<Operation> operations = operationRepository.findByAccountIdOrderByDateDesc(saveOperationRequest.getAccountId());
                if(OperationFacade.checkWithdraw(saveOperationRequest, OperationFacade.accountBalance(operations))){
                    savedOperation =  operationRepository.save(OperationMapper.map(saveOperationRequest));
                }
            }
            return savedOperation;
        } catch(IngBankException e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
