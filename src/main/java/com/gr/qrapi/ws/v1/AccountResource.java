package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.service.AccountService;
import com.gr.qrapi.core.service.AccountServiceLocal;

/**
 * @author Muhammad Mohsin Zafar
 */
@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	@Context
	private UriInfo uriInfo;
	
	AccountServiceLocal accountService = AccountService.getService();

	@GET
	public List<Account> getAllAccounts() {
		
		return accountService.getAllAccounts();
	}
	
	@GET
	@Path("/{accountID}")
	public Account getAccountById(@PathParam("accountID") int id ) {
		return accountService.getAccountById(id);
	}
	
	@POST
	public Response verifyAccount(Account account) {
		account = accountService.verifyAccount(account.getUserName(), account.getPassWord());
		if (account == null) {
			return Response.status(Status.BAD_REQUEST).entity(account).build();
		}
		return Response.ok(uriInfo.getRequestUri()).entity(account).build();
	}
}
