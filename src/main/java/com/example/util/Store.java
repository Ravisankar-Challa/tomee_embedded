package com.example.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.rest.model.misc.Data;


/**
 * @author Ravisankar C
 *
 */
public class Store {
	//This is filled from Data titles, but in case insensitive format
	private SortedMap<String, String> titles =  new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	private List<FlightRange> codeShareFlightRanges;
	private List<FlightRange> internationalFlightRanges;
	private Data miscInfo;
	
	public SortedMap<String, String> getTitles() {
		return titles;
	}

	public Data getMiscInfo() {
		return miscInfo;
	}

	public void setMiscInfo(Data miscInfo) {
		this.miscInfo = miscInfo;
	}

	public List<FlightRange> getCodeShareFlightRanges() {
		return codeShareFlightRanges;
	}

	public void setCodeShareFlightRanges(List<FlightRange> codeShareFlightRanges) {
		this.codeShareFlightRanges = codeShareFlightRanges;
	}

	public List<FlightRange> getInternationalFlightRanges() {
		return internationalFlightRanges;
	}

	public void setInternationalFlightRanges(List<FlightRange> internationalFlightRanges) {
		this.internationalFlightRanges = internationalFlightRanges;
	}
	
}
