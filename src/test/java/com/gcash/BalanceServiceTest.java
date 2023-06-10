package com.gcash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalanceServiceTest {

    @Test
    void getBalanceTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService transaction = new BalanceService(repository);

        //Kick
        String accountId = repository.createAccount("Orvyl", 89.9);

        Assertions.assertEquals(89.9, transaction.getBalance(accountId)) ;
        Assertions.assertNotNull(transaction.getBalance(accountId));

    }

    @Test
    void getBalanceTest_AccountNotFound(){
        AccountRepository repository = new AccountRepository();
        BalanceService transaction = new BalanceService(repository);

        //Kick
        String accountId = repository.createAccount("Orvyl", 89.9);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> transaction.getBalance("NonExisting-ID")
                );

    }

    @Test
    void debitTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService transaction = new BalanceService(repository);

        //Kick
        String accountId = repository.createAccount("Orvyl", 89.9);

        Double initialBalance = transaction.getBalance(accountId);
        transaction.debit(accountId, 100.0) ;

        Assertions.assertEquals((initialBalance-100), transaction.getBalance(accountId)) ;
    }

    @Test
    void creditTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService transaction = new BalanceService(repository);

        //Kick
        String accountId = repository.createAccount("Orvyl", 89.9);

        Double initialBalance = transaction.getBalance(accountId);
        transaction.credit(accountId, 100.0) ;

        Assertions.assertEquals((initialBalance+100), transaction.getBalance(accountId)) ;
    }

    @Test
    void transferTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService transaction = new BalanceService(repository);

        //Kick
        String accountIdFrom = repository.createAccount("Orvyl", 89.9);
        String accountIdTo = repository.createAccount("Ian", 79.9);

        Double initialBalanceFrom = transaction.getBalance(accountIdFrom);
        Double initialBalanceTo = transaction.getBalance(accountIdTo);
        transaction.transfer(accountIdFrom, accountIdTo, 20.5) ;

        Assertions.assertEquals((initialBalanceFrom-20.5), transaction.getBalance(accountIdFrom)) ;
        Assertions.assertEquals((initialBalanceTo+20.5), transaction.getBalance(accountIdTo)) ;
    }

    //TransferTest insufficient Balance
    @Test
    void transferTest_insufficientBalance() {
        AccountRepository repository = new AccountRepository();
        BalanceService transaction = new BalanceService(repository);

        //Kick
        String accountIdFrom = repository.createAccount("Orvyl", 89.9);
        String accountIdTo = repository.createAccount("Ian", 79.9);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> transaction.transfer(accountIdFrom, accountIdTo, 100.0)
        ) ;

    }
}
