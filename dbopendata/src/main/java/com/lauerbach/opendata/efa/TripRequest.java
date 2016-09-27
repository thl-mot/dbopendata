package com.lauerbach.opendata.efa;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * @author thomas
 *
 */
public class TripRequest {
	static final String baseUrl = "http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?";

	String language = "de";

	private String origin_name = "";
	private String origin_place = "";
	private Type origin_type = Type.ADDRESS;
	private String destination_name;
	private String destination_place = "";
	private Type destination_type = Type.ADDRESS;

	public enum Type {
		ADDRESS("address");
		String value;

		Type(String str) {
			value = str;
		}
	}

	public TripRequest( String o_Name, String d_Name) {
		this.origin_name= o_Name;
		this.destination_name= d_Name;
	}
	
	public URL getURL() {
		try {
			String urlStr = baseUrl;
			urlStr += "language=" + language;

			urlStr += "&place_origin="+URLEncoder.encode( origin_place, "UTF-8");
			urlStr += "&name_origin=" + URLEncoder.encode(origin_name, "UTF-8");
			urlStr += "&type_origin=" + Type.ADDRESS.value;
			urlStr += "&place_destination="+URLEncoder.encode( destination_place, "UTF-8");
			urlStr += "&name_destination=" + URLEncoder.encode(destination_name, "UTF-8");
			urlStr += "&type_destination=" + Type.ADDRESS.value;
			return new URL(urlStr);
		} catch (UnsupportedEncodingException | MalformedURLException e) {
			return null;
		}
	}
}
