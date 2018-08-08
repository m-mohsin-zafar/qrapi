package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Account;

/**
 * @author Muhammad Mohsin 
 */
@Local
public interface AccountServiceLocal {

	List<Account> getAllAccounts();
	
	Account getAccountById(int id);
	
	Account verifyAccount(String username, String password);
	
}
