package com.lauerbach.dbopendata.bahnde.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "JourneyDetail")
public class JourneyDetail {

	List<Stop> stops;
	List<Name> names;
	List<Type> types;
	List<Name> operators;
	List<Note> notes;

	@XmlElementWrapper(name = "Stops")
	@XmlElement(name = "Stop")
	public List<Stop> getStops() {
		return stops;
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

	@XmlElementWrapper(name = "Names")
	@XmlElement(name = "Name")
	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	@XmlElement(name = "Type")
	@XmlElementWrapper(name = "Types")
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	@XmlElement(name = "Operator")
	@XmlElementWrapper(name = "Operators")
	public List<Name> getOperators() {
		return operators;
	}

	public void setOperators(List<Name> operators) {
		this.operators = operators;
	}

	@XmlElement(name = "Note")
	@XmlElementWrapper(name = "Notes")
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
