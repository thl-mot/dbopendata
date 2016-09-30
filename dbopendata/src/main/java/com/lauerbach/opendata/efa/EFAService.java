package com.lauerbach.opendata.efa;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.lauerbach.opendata.efa.DmRequest.StationBoardEnum;
import com.lauerbach.opendata.efa.xml.ItdRequest;

/**
 * http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?language=de&place_destination=
 * Stuttgart&name_destination=Schelmenwasen&type_destination=address&
 * place_origin=Karlsruhe&name_origin=Jaegerhaus&type_origin=address
 * 
 * Schema https://devutilsonline.com/xsd-xml/generate-xsd-from-xml
 * 
 * @author thomas
 *
 */

public class EFAService {

	private String baseDmRequestURL = "http://efa-bw.de/nvbw/XML_DM_REQUEST";

	private ItdRequest getXMLResponse(URL requestUrl) {
		try {
			System.out.println("Request: " + requestUrl.toString());
			Object response = null;
			JAXBContext context;
			context = JAXBContext.newInstance(ItdRequest.class);
			Unmarshaller um = context.createUnmarshaller();
			// um.setEventHandler(new
			// javax.xml.bind.helpers.DefaultValidationEventHandler());
			// System.out.println("Request " + requestUrl.toString());
			InputStream is = new BufferedInputStream(requestUrl.openStream());
			response = um.unmarshal(is);
			is.close();
			return (ItdRequest) response;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?language=de&place_destination=Stuttgart&name_destination=Schelmenwasen&type_destination=address&place_origin=Karlsruhe&name_origin=Jaegerhaus&type_origin=address
	public ItdRequest getTrip(String origin, String destination)
			throws MalformedURLException, UnsupportedEncodingException {
		TripRequest tripRequest = new TripRequest(origin, destination);
		URL url = tripRequest.getURL();
		return getXMLResponse(url);
	}

	// http://efa-bw.de/nvbw/XML_DM_REQUEST?sessionID=0&requestID=0&language=de&command=&execInst=&stateless=1&itdLPxx_frames=&useRealtime=1&depType=stopEvents&includeCompleteStopSeq=1&ptOptionsActive=1&locationServerActive=1&convertStopsPTKernel2LocationServer=1&convertAddressesITKernel2LocationServer=1&convertCoord2LocationServer=1&convertCrossingsITKernel2LocationServer=1&convertPOIsITKernel2LocationServer=1&anySigWhenPerfectNoOtherMatches=1&anyMaxSizeHitList=350&w_objPrefAm=2&w_regPrefAl=8&prMinQu=1&selectAssignedStops=1&useProxFootSearch=0&type_dm=any&nameDefaultText_dm=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&nameInfo_dm=invalid&placeInfo_dm=invalid&typeInfo_dm=invalid&placeOMC_dm=&nameState_dm=notidentified&placeState_dm=empty&nameX_dm=&nameY_dm=&nameDisplay_dm=Karlsruhe%2C+Tullastra%DFe&typeDisplay_dm=&itdLPxx_id_dm=%3Adm&anyObjFilter_dm=2&name_dm=Karlsruhe%2C+Tullastra%DFe&place_dm=&poi_main_dm=1&poi_sub_dm=1&poi_point_dm=1&itdDateDay=29&itdDateMonth=09&itdDateYear=2016&itdTimeHour=09&itdTimeMinute=13&itdDateTimeDepArr=dep
	public ItdRequest getStationBoard(StationBoardEnum type, String name) {
		DmRequest dmRequest = new DmRequest(type, name);
		dmRequest.useRealtime = true;
		ItdRequest response = getXMLResponse(dmRequest.getURL(baseDmRequestURL));
		return response;
	}

	// http://efa-bw.de/nvbw/XML_DM_REQUEST?sessionID=BW-WW0579802379&requestID=1&language=de&command=&execInst=&stateless=1&itdLPxx_frames=&useRealtime=1&depType=stopEvents&includeCompleteStopSeq=1&ptOptionsActive=1&nameX_dm=3458464&nameY_dm=729943&nameDisplay_dm=Karlsruhe%2C+Tullastra%DFe&typeDisplay_dm=stop&dmLineSelectionAll=0&dmLineSelection=1%3A0&dmLineSelection=1%3A1&dmLineSelection=1%3A2&dmLineSelection=1%3A3&itdDateTimeDepArr=dep
	public ItdRequest getStationBoardLine(String sessionID, String lineSelection) {
		DmRequest dmRequest = new DmRequestLineSelection(sessionID, lineSelection);
		dmRequest.useRealtime = true;
		dmRequest.itdDateTimeDepArr= StationBoardEnum.ARRIVAL;
		ItdRequest response = getXMLResponse(dmRequest.getURL(baseDmRequestURL));
		return response;
	}

}
