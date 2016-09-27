package com.lauerbach.dbopendata.bahnde;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.lauerbach.dbopendata.bahnde.xml.ArrivalBoard;
import com.lauerbach.dbopendata.bahnde.xml.DepartureBoard;
import com.lauerbach.dbopendata.bahnde.xml.JourneyDetail;
import com.lauerbach.dbopendata.bahnde.xml.LocationList;
import com.lauerbach.dbopendata.bahnde.xml.Name;
import com.lauerbach.dbopendata.bahnde.xml.Note;
import com.lauerbach.dbopendata.bahnde.xml.Type;

/**
 * 
 * http://download-data.deutschebahn.com/static/apis/fahrplan/Fpl-API-Doku-Open-Data-BETA-0_81_2.pdf
 * 
 * https://open-api.bahn.de/bin/rest.exe
 * 
 * @author thomas
 *
 */
public class DBOpenDataService {
	static final String AUTH_KEY="DBhackFrankfurt0316";
	
	// http://download-data.deutschebahn.com/static/apis/fahrplan/Fpl-API-Doku-Open-Data-BETA-0_81_2.pdf

	public enum StationBoardType {

		departure("departureBoard"), arrival("arrivalBoard");

		private String name;

		StationBoardType(String str) {
			this.name = str;
		}

		public String getName() {
			return name;
		}
	}

	String authKey = null;
	String format = "xml"; // json, xml
	String lang = "de"; // De, Da, En, Es, fr, it, nl, pl

	public final String baseUrlStr = "https://open-api.bahn.de/bin/rest.exe";

	public DBOpenDataService() {
		this.authKey = System.getProperty("com.lauerbach.dbopendata.authKey", "DBhackFrankfurt0316");
	}

	public DBOpenDataService(String authKey) {
		this.authKey = authKey;
	}

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

	public Object getXMLResponse(URL requestUrl) {
		try {
			Object response = null;
			JAXBContext context;
			context = JAXBContext.newInstance( DepartureBoard.class, ArrivalBoard.class, LocationList.class, JourneyDetail.class );
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

	public LocationList getLocationName(String input) {
		try {
			String urlStr = baseUrlStr + "/location.name";
			urlStr += "?authKey=" + authKey;
			urlStr += "&format=" + format;
			urlStr += "&input=" + URLEncoder.encode(input, "UTF-8");
			URL url = new URL(urlStr);

			// saveUrl("log/locationName.xml", url);
			return (LocationList) getXMLResponse( url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

	private Object getStationBoard(StationBoardType svc, String stationId, Date date) {
		try {
			String urlStr = baseUrlStr + "/" + svc.getName();
			urlStr += "?authKey=" + authKey;
			urlStr += "&format=" + format;
			urlStr += "&id=" + stationId;
			urlStr += "&date=" + sdfDate.format(date);
			urlStr += "&time=" + sdfTime.format(date);
			URL url = new URL(urlStr);

			// saveUrl("log/" + svc.getName() + ".xml", url);
			return getXMLResponse( url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public DepartureBoard getDepartureBoard(String stationId, Date date) {
		return (DepartureBoard) getStationBoard(StationBoardType.departure, stationId, date);
	}

	public ArrivalBoard getArrivalBoard(String stationId, Date date) {
		return (ArrivalBoard) getStationBoard(StationBoardType.arrival, stationId, date);
	}

	public JourneyDetail getJourneyDetails(URL journeyDetailsUrl) {
		try {
			// saveUrl("log/journeyDetailsUrl.xml", journeyDetailsUrl);
			JourneyDetail result= (JourneyDetail) getXMLResponse( journeyDetailsUrl);
			
			Iterator<Name> ni = result.getNames().iterator();
			while (ni.hasNext()) {
				Name n = ni.next();
				for (int i=n.getRouteIdxFrom(); i<n.getRouteIdxTo(); i++) {
					result.getStops().get(i).setTrainName( n);
				}
			}

			Iterator<Name> oi = result.getOperators().iterator();
			while (ni.hasNext()) {
				Name n = oi.next();
				for (int i=n.getRouteIdxFrom(); i<n.getRouteIdxTo(); i++) {
					result.getStops().get(i).setOperator( n);
				}
			}

			Iterator<Type> ti = result.getTypes().iterator();
			while (ni.hasNext()) {
				Type n = ti.next();
				for (int i=n.getRouteIdxFrom(); i<n.getRouteIdxTo(); i++) {
					result.getStops().get(i).setType( n);
				}
			}

			Iterator<Note> noi = result.getNotes().iterator();
			while (ni.hasNext()) {
				Note n = noi.next();
				for (int i=n.getRouteIdxFrom(); i<n.getRouteIdxTo(); i++) {
					result.getStops().get(i).setNote( n);
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

	}

}
