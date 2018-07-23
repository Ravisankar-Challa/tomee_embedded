package com.example.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class RestClientProducer1 {

	private static Logger LOG = Logger.getLogger(RestClientProducer1.class.getName());
	private static Map<String, Client> clientMap = new HashMap<>();
	
	public RestClientProducer1() {
		LOG.info("Indside RestClientProducer constructor");
	}
	
	@PostConstruct
	public void init() {
		LOG.info("Indside RestClientProducer PostConstruct");
	}
	
	//@Produces
	public Client createClient(InjectionPoint ip) {
		LOG.info(ip.getMember().getDeclaringClass().getName()+" requests client injection");
		String injectionPoint = ip.getMember().getDeclaringClass().getName();
		if(clientMap.containsKey(injectionPoint)) {
			return clientMap.get(injectionPoint);
		}
		clientMap.put(injectionPoint, ClientBuilder.newClient());
		return clientMap.get(injectionPoint);
		
	}
	
	@PreDestroy
	public void destroy() {
		LOG.info("Indside RestClientProducer destroy");
	}
}
