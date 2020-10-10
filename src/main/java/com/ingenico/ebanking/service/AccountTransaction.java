package com.ingenico.ebanking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ingenico.ebanking.account.Account;
import com.ingenico.ebanking.account.CurrentAccount;
import com.ingenico.ebanking.exception.ResponseException;
import com.ingenico.ebanking.model.AccountModel;
import com.ingenico.ebanking.model.TransferModel;

/**
 *
 * @author caydogdu
 *
 *         This is a service for account transactions
 */
@Service
public class AccountTransaction implements Transaction {

    private static final Logger logger = LoggerFactory.getLogger(AccountTransaction.class);

    List<Account> accounts = new ArrayList<>();

    public void displayAccounts() {
        getAccounts()
                .forEach(acc -> logger.info(String.format("name: %s, balance: %s", acc.getName(), acc.getBalance())));
    }

    public Account getAccount(String name) {
        Optional<Account> account = accounts.stream().filter(acc -> acc.getName().equals(name)).findFirst();
        if (account.isPresent()) {
            return account.get();
        }
        return null;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account openAccount(AccountModel acc) {
        Account account = new CurrentAccount(acc.getBalance(), acc.getName());
        accounts.add(account);
        return account;
    }

    public boolean transfer(TransferModel tm) throws ResponseException {
        Account accountFrom = getAccount(tm.getNameFrom());
        if (accountFrom == null) {
            throw new ResponseException("EBanking-02", "account " + tm.getNameFrom() + " not fond", null);
        }
        Account accountTo = getAccount(tm.getNameTo());
        if (accountTo == null) {
            throw new ResponseException("EBanking-02", "account " + tm.getNameTo() + " not fond", null);
        }
        if (accountFrom.getBalance() >= tm.getAmount()) {
            accountFrom.withdraw(tm.getAmount());
            accountTo.deposit(tm.getAmount());
            return true;
        } else {
            throw new ResponseException("EBanking-01", "Balance is not enough to transfer", null);
        }
    }
}
