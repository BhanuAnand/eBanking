package com.ingenico.ebanking.account;

/**
 *
 * @author caydogdu
 *
 *         This is a abstract class for possible accounts
 *
 */
public abstract class AbstractAccount implements Account {

    protected double balance;

    protected String name;

    public AbstractAccount(double balance, String name) {
        this.balance = balance;
        this.name = name;
    }

}
