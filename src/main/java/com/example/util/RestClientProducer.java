package com.example.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * @author Ravisankar C
 *
 */
@ApplicationScoped
public class RestClientProducer {
	
	private Logger LOG = Logger.getLogger(RestClientProducer.class.getName());
	private static Map<String, Client> clientsMap = new HashMap<>();
	
	public RestClientProducer() {
		LOG.info("Indside RestClientProducer constructor");
	}
	
	@PostConstruct
	public void init() {
		LOG.info("Indside RestClientProducer PostConstruct");
	}
	
	@Produces
	@RestClient
	public Client createClient(InjectionPoint ip) {
		String injectedClazzName = ip.getMember().getDeclaringClass().getName();
		if(clientsMap.containsKey(injectedClazzName)) {
			return clientsMap.get(injectedClazzName);
		}
		boolean useProxy = ip.getAnnotated().getAnnotation(RestClient.class).useProxy();
		int readTimeout = ip.getAnnotated().getAnnotation(RestClient.class).readTimeout();
		int connectTimeout = ip.getAnnotated().getAnnotation(RestClient.class).connectTimeout();
		int maxConnectionsPerRoute = ip.getAnnotated().getAnnotation(RestClient.class).maxConnectionsPerRoute();
		LOG.info("Creating the JAX-RS rest client userProxy : "+useProxy
				+", readTimeout : "+readTimeout+", connectTimeout : "+ connectTimeout);
		/*if(System.getProperty("jboss.host.name") != null) {
			ResteasyClientBuilder clientBuilder =  new ResteasyClientBuilder()
					.maxPooledPerRoute(maxConnectionsPerRoute)
					.connectionPoolSize(500)
					.connectionTTL(300, TimeUnit.SECONDS)
					.establishConnectionTimeout(connectTimeout, TimeUnit.SECONDS)
					.socketTimeout(readTimeout, TimeUnit.SECONDS);
			if(useProxy) {
					clientBuilder.defaultProxy(System.getProperty("https.proxyHost"), 
							Integer.parseInt(System.getProperty("https.proxyPort")));
			}
			clientsMap.put(injectedClazzName, clientBuilder.build());
			return clientsMap.get(injectedClazzName);
		}
		else {*/
			ClientBuilder clientBuilder = ClientBuilder.newBuilder();
			 //For Jersey Implementation
			clientBuilder.property("jersey.config.client.connectTimeout", connectTimeout); // in milli seconds
			clientBuilder.property("jersey.config.client.readTimeout", readTimeout); // in milli seconds
	        	//For Apache CXF Implementation
			clientBuilder.property("http.connection.timeout", String.valueOf(connectTimeout)); 
			clientBuilder.property("http.receive.timeout", String.valueOf(readTimeout));
			if(useProxy) {
				clientBuilder.property("http.proxy.server.uri", System.getProperty("https.proxyHost"));
				clientBuilder.property("http.proxy.server.port", System.getProperty("https.proxyPort"));
			}
			 //For IBM Implementation
			clientBuilder.property("com.ibm.ws.jaxrs.client.timeout", String.valueOf(readTimeout));//in milli seconds
			if(useProxy) {
				clientBuilder.property("com.ibm.ws.jaxrs.client.proxy.host", System.getProperty("https.proxyHost"));
				clientBuilder.property("com.ibm.ws.jaxrs.client.proxy.port", System.getProperty("https.proxyPort"));
			}
			clientsMap.put(injectedClazzName, clientBuilder.build());
			return clientsMap.get(injectedClazzName);
		}
	//}
	
	@PreDestroy
	public void destroy() {
		LOG.info("Indside RestClientProducer destroy");
	}
}
