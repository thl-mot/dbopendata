package com.lauerbach.opendata.bahnde.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Departure {

	String name;
	String type;
	long stopid;
	String stop;

	String date, time;

	String direction;

	String track;

	JourneyDetailRef journeyDetailRef;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute(name = "stopid")
	public long getStopid() {
		return stopid;
	}

	public void setStopid(long stopid) {
		this.stopid = stopid;
	}

	@XmlAttribute(name = "stop")
	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	@XmlAttribute(name = "date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@XmlAttribute(name = "time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@XmlAttribute(name = "direction")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@XmlAttribute(name = "track")
	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	@XmlElement(name = "JourneyDetailRef")
	public JourneyDetailRef getJourneyDetailRef() {
		return journeyDetailRef;
	}

	public void setJourneyDetailRef(JourneyDetailRef journeyDetailRef) {
		this.journeyDetailRef = journeyDetailRef;
	}

}
