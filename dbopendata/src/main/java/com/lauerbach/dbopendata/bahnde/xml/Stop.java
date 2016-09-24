package com.lauerbach.dbopendata.bahnde.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class Stop {
	String name;
	long id;
	double lat, lon;
	long routeIdx;
	String depTime, depDate;
	String arrTime, arrDate;
	String track;
	
	Name trainName;
	Type type;
	Name operator;
	Note note;

	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlAttribute(name="lat")
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@XmlAttribute(name="lon")
	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@XmlAttribute(name="routeIdx")
	public long getRouteIdx() {
		return routeIdx;
	}

	public void setRouteIdx(long routeIdx) {
		this.routeIdx = routeIdx;
	}

	@XmlAttribute(name="depTime")
	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	@XmlAttribute(name="depDate")
	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	@XmlAttribute(name="arrTime")
	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	@XmlAttribute(name="arrDate")
	public String getArrDate() {
		return arrDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}

	@XmlAttribute(name="track")
	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	@XmlTransient
	public Name getTrainName() {
		return trainName;
	}

	public void setTrainName(Name trainName) {
		this.trainName = trainName;
	}

	@XmlTransient
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlTransient
	public Name getOperator() {
		return operator;
	}

	public void setOperator(Name operator) {
		this.operator = operator;
	}

	@XmlTransient
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
}
