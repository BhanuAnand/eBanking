package com.ingenico.ebanking;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ingenico.ebanking.account.Account;
import com.ingenico.ebanking.exception.ResponseException;
import com.ingenico.ebanking.model.AccountModel;
import com.ingenico.ebanking.model.TransferModel;
import com.ingenico.ebanking.service.AccountTransaction;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class AccountTransactionTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountTransactionTest.class);
	
	@Autowired
	private AccountTransaction accountTransaction;
	
	/**
	 * This is a test method to open accounts
	 * @throws Exception
	 */
	@Test
	public void openAccount() throws Exception {

		AccountModel account1 = new AccountModel();
		account1.setBalance(100);
		account1.setName("cemil");
		
		Account acc1 = accountTransaction.openAccount(account1);
		assertEquals("cemil", acc1.getName());
		
		AccountModel account2 = new AccountModel();
		account2.setBalance(150);
		account2.setName("burcu");
		
		accountTransaction.openAccount(account2);
		assertEquals(2, accountTransaction.getAccounts().size());
		
	}
	
	/**
	 * This is a test method to transfer
	 * @throws Exception
	 */
	@Test
	public void transfer() throws Exception {

		accountTransaction.displayAccounts();
		
		TransferModel tm = new TransferModel();
		tm.setNameFrom("cemil");
		tm.setNameTo("burcu");
		tm.setAmount(10);
		
		accountTransaction.transfer(tm);
		
		accountTransaction.displayAccounts();

	}
	
	/**
	 * This is a test method to simultaneous transfer
	 * @throws Exception
	 */
	@Test
	public void simultaneousTransfer() throws Exception {
		
		accountTransaction.displayAccounts();
		
		TransferModel tm = new TransferModel();
		tm.setNameFrom("cemil");
		tm.setNameTo("burcu");
		tm.setAmount(40);
		
		Thread t1 = new Thread(new ParallelTask(accountTransaction, tm), "Thread - T1");
		Thread t2 = new Thread(new ParallelTask(accountTransaction, tm), "Thread - T2");
		Thread t3 = new Thread(new ParallelTask(accountTransaction, tm), "Thread - T3");
		
		t1.start();
		t2.start();
		t3.start();
		
		assertEquals(true, accountTransaction.getAccount("cemil").getBalance() > 0);

	}
	
	private static class ParallelTask implements Runnable {
		
		AccountTransaction accountTransaction;
		TransferModel tm;
		
		public ParallelTask(AccountTransaction accountTransaction, TransferModel tm){
			this.accountTransaction = accountTransaction;
			this.tm = tm;
		}
		
		@Override
		public void run() {
			logger.info(Thread.currentThread().getName() + " is executing this code");
			try {
				accountTransaction.transfer(tm);
				accountTransaction.displayAccounts();
			} catch (ResponseException e) {
				logger.error(e.getExceptionDescription());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			
		}
	}

}