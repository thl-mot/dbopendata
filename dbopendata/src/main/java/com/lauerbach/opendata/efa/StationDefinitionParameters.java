package com.lauerbach.opendata.efa;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StationDefinitionParameters {

	public enum TravelPoint {
		ORIGIN("_origin"), DESTINATION("_destination"), VIA("_via"), NO_VIA("_novia"), DM("_dm");
		String value;

		TravelPoint(String str) {
			value = str;
		}
	}

	public enum Type {
		ANY("any"), ADDRESS("address");
		String value;

		Type(String str) {
			value = str;
		}
	}

	// type_origin=any&
	Type type = Type.ANY;

	// nameDefaultText_origin=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&
	String nameDefaultText = "";

	// nameInfo_origin=invalid&
	String nameInfo = "invalid";

	// placeInfo_origin=invalid&
	String placeInfo = "invalid";

	// typeInfo_origin=invalid&
	String typeInfo = "invalid";

	// placeOMC_origin=&
	String placeOMC = null;

	// nameState_origin=notidentified&
	String nameState = "notidentified";

	// placeState_origin=notidentified&
	String placeState = "notidentified";

	// nameX_origin=3459635&
	Long nameX = null;

	// nameY_origin=726571&
	Long nameY = null;

	// nameDisplay_origin=Waldstadt+J%E4gerhaus&
	String nameDisplay = null;

	// typeDisplay_origin=&
	String typeDisplay = null;

	// nameValue_origin=Waldstadt+%28Karlsrh.%29+Waldstadt+J%E4gerhaus&
	String nameValue = null;

	// placeValue_origin=&
	String placeValue = null;

	// name_origin=Waldstadt+%28Karlsrh.%29+Waldstadt+J%E4gerhaus&
	String name = null;

	String place = null;

	private TravelPoint travelPoint = null;

	// name_origin=Waldstadt+%28Karlsrh.%29+Waldstadt+J%E4gerhaus&

	// itdLPxx_id_origin=7000409%3Aorigin&
	// anyObjFilter_origin=0&

	public StationDefinitionParameters(TravelPoint travelPoint) {
		this.travelPoint = travelPoint;
	}

	public StationDefinitionParameters(TravelPoint travelPoint, String name) {
		this.travelPoint = travelPoint;
		this.name = name;
		this.type = Type.ANY;
	}

	private String getParam(String name, String value) {
		try {
			return value != null ? "&" + name + travelPoint.value + "=" + URLEncoder.encode(value, "UTF-8") : "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public String getParameters() {
		String str = "";
		str += type != null ? "&type" + travelPoint.value + "=" + type.value : "";
		str += getParam("name", name);
		str += getParam("placeValue", placeValue);
		str += getParam("place", place);

		return str;
	}

}
