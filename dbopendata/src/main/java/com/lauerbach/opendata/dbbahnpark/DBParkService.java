package com.lauerbach.opendata.dbbahnpark;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * http://download-data.deutschebahn.com/static/apis/parkplatz/opendata_dbbahnpark_08.pdf
 * @author thomas
 *
 */
public class DBParkService {

	static final String baseUrl= "http://opendata.dbbahnpark.info/api/beta";
	
	public void saveUrl(final String filename, final URL url) throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(url.openStream());
			fout = new FileOutputStream(filename);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
				System.out.println(new String(data));
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
	}
	
	public Object getJSonResponse( URL url) throws JsonParseException, JsonMappingException, IOException {
		// TODO: Docs https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
		ObjectMapper mapper = new ObjectMapper();
		Object o= mapper.readValue( url, Object.class);
		return o;
	}

	public void getStationList() {
		// baseUrl+"stations"
	}
	
	public void getSiteList() {
		// baseUrl+"sites"
	}

	public void getOccupancy() {
		// baseUrl+"occupancy"{+"/"+siteId}
	}

	
}
