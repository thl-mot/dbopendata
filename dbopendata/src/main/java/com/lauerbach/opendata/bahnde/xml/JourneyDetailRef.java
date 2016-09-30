package com.lauerbach.opendata.bahnde.xml;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.annotation.XmlAttribute;

public class JourneyDetailRef {
	String ref;

	@XmlAttribute( name="ref")
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public URL getURL() throws MalformedURLException {
		return new URL( ref);
	}
}
