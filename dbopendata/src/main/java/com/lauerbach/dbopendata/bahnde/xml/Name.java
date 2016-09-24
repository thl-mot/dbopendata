package com.lauerbach.dbopendata.bahnde.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Name {
	String name;
	int routeIdxFrom;
	int routeIdxTo;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
