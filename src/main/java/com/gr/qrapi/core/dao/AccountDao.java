package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Account;

/**
 * @author Muhammad Mohsin
 */
public interface AccountDao extends GenericDao<Account, Integer> {

	public Account addAccount(String name, String emailAddress, String timeZone);
	public List<Account> viewAllAccounts();
	public Account updateAccount(int id, String name, String emailAddress, String timeZone);
	public void deleteAccount(int id);
}
