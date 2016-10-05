package com.lauerbach.opendata.efa;

import java.net.MalformedURLException;
import java.net.URL;

public class DmRequestLineSelection extends DmRequest {
	
	private String[] lineSelection=null;
	private boolean lineSelectionAll;
	
	public DmRequestLineSelection(String sessionID, String... lineSelection) {
		super( null, null);
		this.sessionID= sessionID;
		this.lineSelection= lineSelection;
		this.station= null;
		this.date= null;
		this.lineSelectionAll= false;
		this.itdDateTimeDepArr= null;
	}
	
	public URL getURL(String baseUrl) {
		try {
			String urlStr = baseUrl;
			urlStr += "?language=" + language;
			urlStr += "&sessionID=" + (sessionID != null ? sessionID : "0");
			urlStr += ParameterHelper.getParameter("requestID", "1");
			urlStr += station != null ? station.getParameters() : "";
			urlStr += ParameterHelper.getParameters(date);
			urlStr += ParameterHelper.getParameter("useRealtime",useRealtime);
			urlStr += ParameterHelper.getParameter("dmLineSelectionAll", lineSelectionAll);
			urlStr += ParameterHelper.getParameter("dmLineSelection", lineSelection);
			urlStr += itdDateTimeDepArr!=null ? "&itdDateTimeDepArr="+itdDateTimeDepArr.getName() : "";
			return new URL(urlStr);
		} catch (MalformedURLException e) {
			return null;
		}
	}

}
