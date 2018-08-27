package com.gr.qrapi.ws.v1;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.gr.qrapi.core.model.DataList;
import com.gr.qrapi.core.model.QRUrl;
import com.gr.qrapi.core.service.UrlShortnerService;
import com.gr.qrapi.core.service.UrlShortnerServiceLocal;

/**
 * @author Muhammad Mohsin Zafar
 */

@Path("/v1/url")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QRUrlResource {

	@Context
	private UriInfo uriInfo;
	
	UrlShortnerServiceLocal urlShortnerService = UrlShortnerService.getService();
	
	@POST
	@Path("/shortenurl")
	public Response addNewShortUrl(QRUrl qrurl) {
		qrurl = urlShortnerService.addShortUrl(qrurl);
		return Response.ok(uriInfo.getRequestUri()).entity(qrurl).build();
	}
	
	@GET
	@Path("/all")
	public List<QRUrl> getAllUrls(){
		return urlShortnerService.getAllUrlsDetail();
	}
	
	@GET
	@Path("/detail")
	public QRUrl getDetailsById(@QueryParam("id") int id) {
		return urlShortnerService.getDetailsById(id);
	}
	
	@GET
	@Path("/verifynroute")
	public String verifyAndRouteUrl(@QueryParam("id") int id) {
		QRUrl qrurl = urlShortnerService.getDetailsById(id);
		Date today = Date.valueOf(LocalDate.now());
		String responseMessage = "";
		if(today.compareTo(qrurl.getExpiryDate()) <=0 ) {
			responseMessage = "{ \"value\" : \""+qrurl.getOriginalUrl()+"\"}";
		}else {
			responseMessage = "{ \"value\" : \"expired\" }";
		}
		return responseMessage;
	}
	
	@GET
	@Path("/totalclicks")
	public int getTotalClicksById(@QueryParam("id") int id) {
		return urlShortnerService.getGrandSumById(id);
	}
	
	@GET
	@Path("/byyearclicks")
	public DataList getByYearClicks(@QueryParam("id") int id) {
		return urlShortnerService.getByYearClicks(id);
	}
	
	@GET
	@Path("/browserclicks")
	public DataList getBrowserClicks(@QueryParam("id") int id) {
		return urlShortnerService.getBrowserClicks(id);
	}
	
	@GET
	@Path("/platformclicks")
	public DataList getPlatformClicks(@QueryParam("id") int id) {
		return urlShortnerService.getPlatformClicks(id);
	}
}
