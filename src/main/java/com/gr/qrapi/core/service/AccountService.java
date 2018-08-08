package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.AccountDaoHibernateImpl;
import com.gr.qrapi.core.model.Account;

/**
 * @author Muhammad Mohsin Zafar
 */
@Stateless
public class AccountService implements AccountServiceLocal {


	public static AccountServiceLocal getService() {
		return (AccountServiceLocal) ServiceManager.getService(AccountServiceLocal.class);
	}
	
	@Override
	public List<Account> getAllAccounts() {
		return AccountDaoHibernateImpl.getDao().viewAllAccounts();
	}
	
	@Override 
	public Account getAccountById(int id) {
		return AccountDaoHibernateImpl.getDao().getById(id);
	}
	
	@Override
	public Account verifyAccount(String username, String password) {
		
		Account account = AccountDaoHibernateImpl.getDao().searchByUsername(username);
		if(account != null) {
			if (account.getPassWord().equals(password)) {
				return account;
			}
		}
		
		return null;
		
	}
	
	
}
