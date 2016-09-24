package com.lauerbach.dbopendata.bahnde.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Type {
	String type;
	int routeIdxFrom;
	int routeIdxTo;

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String name) {
		this.type = name;
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
