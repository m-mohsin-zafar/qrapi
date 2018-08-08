package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Account;

/**
 * @author Muhammad Mohsin
 */
public interface AccountDao extends GenericDao<Account, Integer> {

	public Account addAccount(Account account);
	public List<Account> viewAllAccounts();
	public Account getById(int id);
	public Account updateAccount(int id, Account account);
	public void deleteAccount(int id);
	public Account searchByUsername(String username);
}