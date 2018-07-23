package com.example.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.service.DataClient;
import com.example.util.GZIPDecoder;
import com.example.util.RestClient;

@Path("/")
public class RestClientTestAsyncResource {

	@Inject
	@RestClient
	private Client client;	
	
	@Inject
	private DataClient miscClient;
	
	@GET
	@Path("client/async")
	@Produces(MediaType.TEXT_HTML)
	public void callAsync(@Suspended final AsyncResponse asyncResponse) {
		client.target("https://www.google.co.in")
			  .register(GZIPDecoder.class)
			  .request()
			  .header("Accept-Encoding", "gzip,deflate")
			  .async()
			  .get(new InvocationCallback<String>() {
					@Override
					public void completed(String response) {
						asyncResponse.resume(response);
					}
					@Override
					public void failed(Throwable throwable) {
						System.out.println(throwable);
						asyncResponse.resume(Response.status(Status.INTERNAL_SERVER_ERROR)
								.entity("Failed").build());
					}
			  });
	}	
	
	@GET
	@Path("client/sync")
	@Produces(MediaType.TEXT_HTML)
	public String callSync() {
		return client.target("https://www.google.co.in")
					 .register(GZIPDecoder.class)
					 .request()
					 .header("Accept-Encoding", "gzip,deflate")
					 .get(String.class);
	}	
	
	@GET
	@Path("client/test")
	public Response callExternalSync() {
		return Response.ok(miscClient.getData()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}	
}
