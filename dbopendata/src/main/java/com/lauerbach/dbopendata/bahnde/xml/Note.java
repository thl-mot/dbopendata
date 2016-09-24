package com.lauerbach.dbopendata.bahnde.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Note {
	int routeIdxFrom;
	int routeIdxTo;
	
	String key;
	String priority;

	String value;
	
	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute( name="key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@XmlAttribute( name="priority")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@XmlAttribute(name = "routeIdxFrom")
	public int getRouteIdxFrom() {
		return routeIdxFrom;
	}

	public void setRouteIdxFrom(int routeIdxFrom) {
		this.routeIdxFrom = routeIdxFrom;
	}

	@XmlAttribute(name = "routeIdxTo")
	public int getRouteIdxTo() {
		return routeIdxTo;
	}

	public void setRouteIdxTo(int routeIdxTo) {
		this.routeIdxTo = routeIdxTo;
	}

}
