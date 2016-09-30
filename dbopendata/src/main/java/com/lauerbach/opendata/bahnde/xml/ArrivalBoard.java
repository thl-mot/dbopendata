package com.lauerbach.opendata.bahnde.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ArrivalBoard")
public class ArrivalBoard {

	List<Arrival> arrivals;

	@XmlElement(name="Arrival")
	public List<Arrival> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrival> departure) {
		this.arrivals = departure;
	}
	
	
	
}
