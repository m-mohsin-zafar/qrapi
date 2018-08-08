package com.gr.qrapi.ws.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.gr.qrapi.core.model.Contact;
import com.gr.qrapi.core.service.ContactService;
import com.gr.qrapi.core.service.ContactServiceLocal;

/**
 * @author Muhammad Mohsin Zafar
 */
@Path("/v1/accounts/{accountid}/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

	@Context
	private UriInfo uriInfo;
	ContactServiceLocal contactService = ContactService.getService();

//	@GET
//	public List<Contact> getAllContacts() {
//
//		return contactService.getAllContacts();
//	}

	@GET
	@Path("/list")
	public List<Contact> getContactsById(@PathParam("accountid") int accountID) {

		return contactService.getContactById(accountID);
	}

	@POST
	public Response addContact(Contact contact, @PathParam("accountid") int accountId) {
		contact = contactService.addContact(contact, accountId);
		if (contact == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.created(uriInfo.getRequestUri()).entity(contact).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateContact(Contact contact, @PathParam("id") int id) {
		contact = contactService.updateContact(contact, id);
		if (contact == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(contact).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteContact(@PathParam("id") int id) {
		// delete contact from database
		return Response.ok().build();
	}
}
