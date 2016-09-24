package com.lauerbach.dbopendata.bahnde.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LocationList")
public class LocationList {

	List<StopLocation> location;

	@XmlElement(name = "StopLocation")
	public List<StopLocation> getStopLocation() {
		return location;
	}

	public void setStopLocation(List<StopLocation> location) {
		this.location = location;
	}

}
