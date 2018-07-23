package com.example.service;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;

import com.example.rest.JsonContentTypeResponseFilter;
import com.example.rest.model.misc.Data;
import com.example.util.Cache;
import com.example.util.FlightRange;
import com.example.util.RestClient;

@ApplicationScoped
public class DataClient {
	
	public static final String url  = "https://raw.githubusercontent.com/Ravisankar-Challa/tomee_embedded/master/src/main/resources/data.js";
	
	@Inject
	@RestClient
	private Client client;

	private static final Logger LOG = Logger.getLogger(DataClient.class.getName());
	
	public Data getData() {
		try {
			// We have registered a JsonContentTypeResponseFilter since the url 
			// Using JsonContentTypeResponseFilter we will override the content type to application/json
			return  client.target(url)
						  .register(JsonContentTypeResponseFilter.class)
					      .request()
					      .get(Data.class);
		} catch(ProcessingException | NotFoundException e) {
			LOG.severe("Calling Data URL :: "+url);
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}
	
	public void updateData() {
		Data miscInfo = getData();
		if(miscInfo != null) {
			Cache.store.setMiscInfo(miscInfo);
			Cache.store.getMiscInfo()
			.getTitles()
			.forEach((key, value) -> {
				Cache.store.getTitles().put(key.trim(), value.trim());
			});
			
			Map<String, String> tempMap = new HashMap<>(Cache.store.getMiscInfo().getAirlines());
			tempMap.forEach((key, value) -> {
						Cache.store.getMiscInfo().getAirlines().put(value.toLowerCase().trim(), key.trim());
			});
			
			Cache.store.setCodeShareFlightRanges(
					miscInfo.getCode_share_flight_range().stream()
					.map(this::createFlightRange)
					.collect(toList()));
			
			Cache.store.setInternationalFlightRanges(
					miscInfo.getInternational_flight_range().stream()
					.map(this::createFlightRange)
					.collect(toList()));
		}	
	}

	private FlightRange createFlightRange(String range) {
		String[] ranges = range.split("-");
		return new FlightRange(Integer.parseInt(ranges[0].trim()), Integer.parseInt(ranges[1].trim()));
	}
}