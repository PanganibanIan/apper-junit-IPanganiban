package com.gcash;

public class BalanceService {
    private final AccountRepository repository;

    BalanceService(AccountRepository repository){
        this.repository = repository ;
    }
    public Double getBalance(String id) {

        if (repository.getAccount(id) == null){
            throw new IllegalArgumentException("ID does not exist");
        } else {
            return repository.getAccount(id).balance();
        }
    }

    public void debit(String id, Double amount) {
        repository.setBalance(id, (repository.getAccount(id).balance() - amount));
    }

    public void credit(String id, Double amount) {
        repository.setBalance(id, (repository.getAccount(id).balance() + amount));
    }

    public void transfer(String from, String to, Double amount) {

        if (getBalance(from)<amount){
            throw new IllegalArgumentException("Insufficient Balance, can't proceed with transaction");
        } else{
            debit(from, amount);
            credit(to, amount) ;
        }

    }

}