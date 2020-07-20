package fr.ing.interview;

import fr.ing.interview.dto.SaveOperationRequest;
import fr.ing.interview.exception.IngBankException;
import fr.ing.interview.facade.OperationFacade;
import fr.ing.interview.model.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void accountBalance(){
        Operation operation1 = new Operation();
        operation1.setAmount(Double.valueOf(50));
        Operation operation2 = new Operation();
        operation2.setAmount(Double.valueOf(15.5));
        Operation operation3 = new Operation();
        operation3.setAmount(Double.valueOf(12));
        Operation operation4 = new Operation();
        operation4.setAmount(Double.valueOf(-15));
        List<Operation> operations = Arrays.asList(operation1, operation2, operation3, operation4);
        OperationFacade.accountBalance(operations);
        Assertions.assertEquals(Double.valueOf(62.5),OperationFacade.accountBalance(operations));
    }

    @Test
    public void checkSaveOperationParameterTestMontant(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(0));
        try{
            OperationFacade.checkSaveOperationParameter(saveOperationRequest);
        } catch (IngBankException e){
            Assertions.assertEquals("Le montant saisi doit être au moins égal à 0.01€", e.getMessage());
        }
    }
    @Test
    public void checkSaveOperationParameterTestAccount(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(0));
        try{
            OperationFacade.checkSaveOperationParameter(saveOperationRequest);
        } catch (IngBankException e){
            Assertions.assertEquals("Erreur Technique - accountId null", e.getMessage());
        }
    }

    @Test
    public void checkSaveOperationParameterTestMissingParameter(){
        SaveOperationRequest saveOperationRequest = null;
        try{
            OperationFacade.checkSaveOperationParameter(saveOperationRequest);
        } catch (IngBankException e){
            Assertions.assertEquals("Les paramètres sont manquants", e.getMessage());
        }
    }
    @Test
    public void checkSaveOperationParameterTestOk(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(1));
        Assertions.assertEquals(true, OperationFacade.checkSaveOperationParameter(saveOperationRequest));
    }


    @Test
    public void checkWithdrawKO(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(15));
        saveOperationRequest.setDeposit(false);
        Double currentAmount = Double.valueOf(10);
        try{
            OperationFacade.checkWithdraw(saveOperationRequest, currentAmount);
        } catch (IngBankException e){
            Assertions.assertEquals("Vous ne pouvez pas retirez un montant supérieur au solde de votre compte", e.getMessage());
        }
    }

    @Test
    public void checkWithdrawOK(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(10));
        saveOperationRequest.setDeposit(false);
        Double currentAmount = Double.valueOf(10);
        Assertions.assertEquals(true, OperationFacade.checkWithdraw(saveOperationRequest, currentAmount));

    }
}
