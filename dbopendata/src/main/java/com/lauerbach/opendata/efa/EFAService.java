package com.lauerbach.opendata.efa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.lauerbach.opendata.efa.xml.ItdRequest;

/**
 * http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?language=de&place_destination=Stuttgart&name_destination=Schelmenwasen&type_destination=address&place_origin=Karlsruhe&name_origin=Jaegerhaus&type_origin=address
 * 
 * Schema
 * https://devutilsonline.com/xsd-xml/generate-xsd-from-xml
 * 
 * @author thomas
 *
 */

public class EFAService {

	
	
	private Object getXMLResponse(URL requestUrl) {
		try {
			Object response = null;
			JAXBContext context;
			context = JAXBContext.newInstance( ItdRequest.class);
			Unmarshaller um = context.createUnmarshaller();
			// um.setEventHandler(new
			// javax.xml.bind.helpers.DefaultValidationEventHandler());
			// System.out.println("Request " + requestUrl.toString());
			InputStream is = new BufferedInputStream(requestUrl.openStream());
			response = um.unmarshal(is);
			is.close();
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
    // http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?language=de&place_destination=Stuttgart&name_destination=Schelmenwasen&type_destination=address&place_origin=Karlsruhe&name_origin=Jaegerhaus&type_origin=address
	public ItdRequest getTrip( String origin, String destination) throws MalformedURLException, UnsupportedEncodingException {
		TripRequest tripRequest= new TripRequest( origin, destination);
		URL url= tripRequest.getURL();
		System.out.println("Request: "+url.toString());
		try {
			Runtime.getRuntime().exec("/usr/bin/firefox "+url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (ItdRequest) getXMLResponse( url);  
	}

}
