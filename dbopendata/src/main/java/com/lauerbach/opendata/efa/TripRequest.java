package com.lauerbach.opendata.efa;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.lauerbach.opendata.efa.StationDefinitionParameters.TravelPoint;

/**
 * 
 * 
 * 
 * 
 * @author thomas
 *
 */
public class TripRequest {
	static final String baseUrl = "http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?";

	String language = "de";

	private StationDefinitionParameters origin, destination, via, noVia;
	
	// itdDateDay=27&
	// itdDateMonth=09&
	// itdDateYear=2016&
	// itdTimeHour=08&
	// itdTimeMinute=53&
	// itdTripDateTimeDepArr=dep&
	// lineRestriction=400&
	// routeType=LEASTTIME&
	// changeSpeed=normal&
	
	public enum DatePoint {
		DEPARTURE("dep"), ARRIVAL("arr");
		String value;
		DatePoint(String str) {
			value = str;
		}
	}

	public TripRequest( String o_Name, String d_Name) {
		this.origin= new StationDefinitionParameters( TravelPoint.ORIGIN, o_Name);
		this.destination= new StationDefinitionParameters( TravelPoint.DESTINATION, d_Name);
	}
	
	public URL getURL() {
		try {
			String urlStr = baseUrl;
			urlStr += "language=" + language;

			urlStr += origin!=null ? origin.getParameters() : "";
			urlStr += destination!=null ? destination.getParameters() : "";
			urlStr += via!=null ? via.getParameters() : "";
			urlStr += noVia!=null ? noVia.getParameters() : "";
			
			return new URL(urlStr);
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
