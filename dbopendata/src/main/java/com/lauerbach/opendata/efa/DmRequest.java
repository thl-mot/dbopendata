package com.lauerbach.opendata.efa;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import com.lauerbach.opendata.efa.StationDefinitionParameters.TravelPoint;

// http://efa-bw.de/nvbw/XML_DM_REQUEST?sessionID=0&
// requestID=0&language=de&
// command=&
// execInst=&
// stateless=1&
// itdLPxx_frames=&
// useRealtime=1&
// depType=stopEvents&
// includeCompleteStopSeq=1&
// ptOptionsActive=1&
// locationServerActive=1&
// convertStopsPTKernel2LocationServer=1&
// convertAddressesITKernel2LocationServer=1&
// convertCoord2LocationServer=1&
// convertCrossingsITKernel2LocationServer=1&
// convertPOIsITKernel2LocationServer=1&
// anySigWhenPerfectNoOtherMatches=1&
// anyMaxSizeHitList=350&
// w_objPrefAm=2&
// w_regPrefAl=8&
// prMinQu=1&
// selectAssignedStops=1&
// useProxFootSearch=0&
// type_dm=any&
// nameDefaultText_dm=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&
// nameInfo_dm=invalid&
// placeInfo_dm=invalid&
// typeInfo_dm=invalid&
// placeOMC_dm=&
// nameState_dm=notidentified&
// placeState_dm=empty&
// nameX_dm=&nameY_dm=&
// nameDisplay_dm=Karlsruhe%2C+Tullastra%DFe&
// typeDisplay_dm=&
// itdLPxx_id_dm=%3Adm&anyObjFilter_dm=2&
// name_dm=Karlsruhe%2C+Tullastra%DFe&place_dm=&poi_main_dm=1&poi_sub_dm=1&poi_point_dm=1&itdDateDay=29&itdDateMonth=09&itdDateYear=2016&itdTimeHour=09&itdTimeMinute=13&itdDateTimeDepArr=dep]]

public class DmRequest {

	String sessionID= null;
	String requestId="0";
	String language = "de";
	Date date = new Date();
	boolean useRealtime;

	StationDefinitionParameters station = new StationDefinitionParameters(TravelPoint.DM);
	
	StationBoardEnum itdDateTimeDepArr= StationBoardEnum.DEPARTURE;

	public enum StationBoardEnum {
		DEPARTURE("dep"), ARRIVAL("arr");
		private String name;
		StationBoardEnum(String str) {
			this.name = str;
		}
		public String getName() {
			return name;
		}
	}
	

	
	public DmRequest(StationBoardEnum type, String name) {
		this.station = new StationDefinitionParameters(TravelPoint.DM, name);
	}

	public URL getURL(String baseUrl) {
		try {
			String urlStr = baseUrl;
			urlStr += "?language=" + language;
			urlStr += "&sessionID=" + (sessionID != null ? sessionID : "0");
			urlStr += ParameterHelper.getParameter("requestID", requestId);
			urlStr += station != null ? station.getParameters() : "";
			urlStr += ParameterHelper.getParameters(date);
			urlStr += ParameterHelper.getParameter("useRealtime",useRealtime);
			urlStr += itdDateTimeDepArr!=null ? "&itdDateTimeDepArr="+itdDateTimeDepArr.name : "";
			
			return new URL(urlStr);
		} catch (MalformedURLException e) {
			return null;
		}
	}

}
