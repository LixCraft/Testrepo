package fr.ing.interview.facade;

import fr.ing.interview.dto.SaveOperationRequest;
import fr.ing.interview.exception.IngBankException;
import fr.ing.interview.model.Operation;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class OperationFacade {

    public static boolean checkSaveOperationParameter(SaveOperationRequest saveOperationRequest){
        if(saveOperationRequest != null){
            if(saveOperationRequest.getAccountId() == 0){
                throw new IngBankException("Erreur Technique - accountId null");
            } else if(saveOperationRequest.getAmount() <=0) {
                throw new IngBankException("Le montant saisi doit être au moins égal à 0.01€");
            }
        } else {
            throw new IngBankException("Les paramètres sont manquants");
        }
        return true;
    }

    public static Double accountBalance(List<Operation> listOperation){
        Double amount = Double.valueOf(0);
        if(!CollectionUtils.isEmpty(listOperation)){
            amount = listOperation.stream().mapToDouble(value-> value.getAmount()).sum();
        }
        return amount;
    }

    public static boolean checkWithdraw(SaveOperationRequest saveOperationRequest, Double accountCurrentAmount){
        if(saveOperationRequest != null && !saveOperationRequest.isDeposit() && !saveOperationRequest.isOverdraft()
            && saveOperationRequest.getAmount().compareTo(accountCurrentAmount) > 0){
            throw new IngBankException("Vous ne pouvez pas retirez un montant supérieur au solde de votre compte");
        }
        return true;
    }
}
