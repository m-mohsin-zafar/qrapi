package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.AccountDaoHibernateImpl;
import com.gr.qrapi.core.model.Account;

/**
 * @author ufarooq
 */
@Stateless
public class GeneralService implements GeneralServiceLocal {


	public static GeneralServiceLocal getService() {
		return (GeneralServiceLocal) ServiceManager.getService(GeneralServiceLocal.class);
	}
	
	@Override
	public List<Account> getAllAccounts() {
		return AccountDaoHibernateImpl.getDao().viewAllAccounts();
	}
}
