package com.lauerbach.opendata.bahnde.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DepartureBoard")
public class DepartureBoard {

	List<Departure> departure;

	@XmlElement(name="Departure")
	public List<Departure> getDeparture() {
		return departure;
	}

	public void setDeparture(List<Departure> departure) {
		this.departure = departure;
	}
	
	
	
}
