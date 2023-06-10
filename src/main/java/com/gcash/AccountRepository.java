package com.gcash;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public String createAccount(String name, Double initialBalance) {
        String id = UUID.randomUUID().toString() ;
        Account account = new Account(id, name, initialBalance);

        accounts.add(account);

        return id;
    }

    public Account getAccount(String id) {

        return accounts
                .stream()
                .filter(account -> id.equals(account.id()))
                .findFirst()
                .orElse(null);

    }

    public void deleteAccount(String id) {
        accounts
                .stream()
                .filter(account -> id.equals(account.id()))
                .findFirst()
                .ifPresent(accounts::remove);

    }

    public void setBalance(String id, Double newBalance) {
        int ctr =0;
        for (Account account : accounts){
            if (account.id().equals(id)) {
                Account updatedAccount = new Account(id, account.name(), newBalance);
                accounts.set(ctr, updatedAccount);
            }
            ctr++;
        }
    }

    public Integer getNumberOfAccounts() {
        return accounts.size();

    }

    public boolean noRegisteredAccount() {
        return accounts.isEmpty() ;
    }
}