package fr.ing.interview;

import static org.junit.Assert.assertTrue;

import fr.ing.interview.controller.AccountController;
import fr.ing.interview.dto.AccountDto;
import fr.ing.interview.dto.SaveOperationRequest;
import fr.ing.interview.exception.IngBankException;
import fr.ing.interview.facade.OperationFacade;
import fr.ing.interview.model.Account;
import fr.ing.interview.model.Operation;
import fr.ing.interview.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
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
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
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
        Assert.assertEquals(Double.valueOf(62.5),OperationFacade.accountBalance(operations));
    }

    @Test
    public void checkSaveOperationParameterTestMontant(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(0));
        try{
            OperationFacade.checkSaveOperationParameter(saveOperationRequest);
        } catch (IngBankException e){
            Assert.assertEquals("Le montant saisi doit être au moins égal à 0.01€", e.getMessage());
        }
    }
    @Test
    public void checkSaveOperationParameterTestAccount(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(0));
        try{
            OperationFacade.checkSaveOperationParameter(saveOperationRequest);
        } catch (IngBankException e){
            Assert.assertEquals("Erreur Technique - accountId null", e.getMessage());
        }
    }

    @Test
    public void checkSaveOperationParameterTestMissingParameter(){
        SaveOperationRequest saveOperationRequest = null;
        try{
            OperationFacade.checkSaveOperationParameter(saveOperationRequest);
        } catch (IngBankException e){
            Assert.assertEquals("Les paramètres sont manquants", e.getMessage());
        }
    }
    @Test
    public void checkSaveOperationParameterTestOk(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(1));
        Assert.assertEquals(true, OperationFacade.checkSaveOperationParameter(saveOperationRequest));
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
            Assert.assertEquals("Vous ne pouvez pas retirez un montant supérieur au solde de votre compte", e.getMessage());
        }
    }

    @Test
    public void checkWithdrawOK(){
        SaveOperationRequest saveOperationRequest = new SaveOperationRequest();
        saveOperationRequest.setAccountId(Long.valueOf(1));
        saveOperationRequest.setAmount(Double.valueOf(10));
        saveOperationRequest.setDeposit(false);
        Double currentAmount = Double.valueOf(10);
        Assert.assertEquals(true, OperationFacade.checkWithdraw(saveOperationRequest, currentAmount));

    }
}
