package dbopendata;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.Iterator;

import org.junit.Test;

import com.lauerbach.dbopendata.bahnde.OpenDataClient;
import com.lauerbach.dbopendata.bahnde.xml.Arrival;
import com.lauerbach.dbopendata.bahnde.xml.ArrivalBoard;
import com.lauerbach.dbopendata.bahnde.xml.Departure;
import com.lauerbach.dbopendata.bahnde.xml.DepartureBoard;
import com.lauerbach.dbopendata.bahnde.xml.JourneyDetail;
import com.lauerbach.dbopendata.bahnde.xml.LocationList;
import com.lauerbach.dbopendata.bahnde.xml.Stop;
import com.lauerbach.dbopendata.bahnde.xml.StopLocation;

public class TestOpenDataClient {

	@Test
	public void test1() {
		OpenDataClient client = new OpenDataClient();
		LocationList l = client.getLocationName("Karlsruhe Hbf");
		Iterator<StopLocation> i = l.getStopLocation().iterator();
		while (i.hasNext()) {
			StopLocation s = i.next();
			System.out.println( s.getName()+" "+s.getLat()+" "+s.getLon()+" "+s.getId());
		}
	}

	@Test
	public void test2() {
		OpenDataClient client = new OpenDataClient();
		ArrivalBoard arrivals = client.getArrivalBoard( "008000191", new Date());
		Iterator<Arrival> i = arrivals.getArrivals().iterator();
		while (i.hasNext()) {
			Arrival a = i.next();
			System.out.println( a.getTime()+" "+a.getName()+" "+a.getOrigin()+" "+a.getType()+"    "+a.getJourneyDetailRef().getRef());
		}
	}
	@Test
	public void test3() {
		OpenDataClient client = new OpenDataClient();
		client.getDepartureBoard( "008000191", new Date());
	}
	@Test
	public void test4() throws MalformedURLException {
		OpenDataClient client = new OpenDataClient();
		LocationList l = client.getLocationName("Karlsruhe HBF");
		StopLocation ka= l.getStopLocation().get(0);
		DepartureBoard departures = client.getDepartureBoard( ka.getId(), new Date());
		Departure departure = departures.getDeparture().get(0);
		
		
		JourneyDetail train = client.getJourneyDetails( departure.getJourneyDetailRef().getURL());
		
		System.out.println( departure.getName()+"-->"+departure.getDirection());
		System.out.println("  "+train.getStops().size());
		
		Iterator<Stop> i = train.getStops().iterator();
		while (i.hasNext()) {
			Stop s = i.next();
			System.out.println( s.getArrTime()+" "+s.getDepTime()+" "+s.getName()+" "+s.getTrack()+" "+s.getNote().getValue());
		}
		
	}

}
