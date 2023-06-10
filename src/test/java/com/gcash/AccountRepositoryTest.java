package com.gcash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {

    @Test
    void successfulAccountCreation() {
        //Setup
        AccountRepository repository = new AccountRepository();

        //Kick
        String accountId = repository.createAccount("Orvyl", 89.9);

        //Verify
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("Orvyl", repository.getAccount(accountId).name());
        Assertions.assertNotNull(accountId);
    }

    @Test
    void getAccountTest() {
        AccountRepository repository = new AccountRepository();

        String accountId = repository.createAccount("Orvyl", 89.9);

        Assertions.assertEquals("Orvyl", repository.getAccount(accountId).name());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).balance());
        Assertions.assertNull(repository.getAccount("randomid")) ;

    }

    @Test
    void successfulDelete() {
        AccountRepository repository = new AccountRepository();
        String id = repository.createAccount("Orvyl", 89.9);

        repository.deleteAccount(id);

        Assertions.assertEquals(0, repository.getNumberOfAccounts());
    }

    @Test
    void successfulGetNumberOfAccounts() {
        //Setup and Kick
        AccountRepository repository = new AccountRepository();
        String id0 = repository.createAccount("Orvyl", 400.4);
        String id1 = repository.createAccount("Ian", 300.3);
        String id2 = repository.createAccount("Patrick", 200.2);
        String id3 = repository.createAccount("John", 100.2);

        //Verify
        Assertions.assertEquals(4, repository.getNumberOfAccounts());

        //Setup
        repository.deleteAccount(id0);

        Assertions.assertEquals(3, repository.getNumberOfAccounts());
    }

    @Test
    void successfullNoRegisteredAccount() {
        AccountRepository repository = new AccountRepository();

        String id = repository.createAccount("Orvyl", 89.9);

        Assertions.assertFalse( repository.noRegisteredAccount());

        repository.deleteAccount(id);

        Assertions.assertTrue( repository.noRegisteredAccount());
    }

}
