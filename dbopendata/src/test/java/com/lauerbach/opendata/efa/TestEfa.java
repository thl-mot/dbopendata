package com.lauerbach.opendata.efa;

import java.util.Iterator;

import org.junit.Test;

import com.lauerbach.opendata.efa.DmRequest.StationBoardEnum;
import com.lauerbach.opendata.efa.xml.ItdArrivalType;
import com.lauerbach.opendata.efa.xml.ItdDepartureType;
import com.lauerbach.opendata.efa.xml.ItdOdvType;
import com.lauerbach.opendata.efa.xml.ItdRequest;
import com.lauerbach.opendata.efa.xml.ItdServingLineType;
import com.lauerbach.opendata.efa.xml.OdvNameElemType;

public class TestEfa {

	@Test
	public void test() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getTrip("Karlsruhe, Jägerhaus", "Manheim");
		System.out.println(result);
		System.out.println(result.getVersion());

		Iterator<ItdOdvType> i = result.getItdTripRequest().getItdOdvs().iterator();
		while (i.hasNext()) {
			ItdOdvType odv = i.next();
			System.out.println(odv.getUsage());
			if (odv.getUsage().equals("origin")) {
				Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
				while (l.hasNext()) {
					OdvNameElemType nameElem = l.next();
					System.out.println("  " + nameElem.getLocality() + "  " + nameElem.getValueAttribute());
				}
			}
		}
	}

	@Test
	public void testDeparture() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getStationBoard(StationBoardEnum.DEPARTURE, "Karlsruhe,Jägerhaus");
		System.out.println(result);
		System.out.println(result.getVersion());

		ItdOdvType odv = result.getItdDepartureMonitorRequest().getItdOdv();
		System.out.println(odv.getUsage());
		Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
		while (l.hasNext()) {
			OdvNameElemType nameElem = l.next();
			System.out.println("  " + nameElem.getLocality() + "  " + nameElem.getValue());
		}
		
		Iterator<ItdServingLineType> i = result.getItdDepartureMonitorRequest().getItdServingLines().getItdServingLines().iterator();
		while (i.hasNext()) {
			ItdServingLineType line = i.next();
			System.out.println( line.getIndex()+" "+line.getTrainName()+" "+line.getDirection());
		}
		
		
	}

	@Test
	public void testDepartureSingleLine() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getStationBoard(StationBoardEnum.DEPARTURE, "Karlsruhe,Jägerhaus");
		System.out.println("Version: "+result.getVersion());
		System.out.println("SessionID: "+result.getVersion());
		
		result = efaService.getStationBoardLine( result.getSessionID(), "4:0");

		ItdOdvType odv = result.getItdDepartureMonitorRequest().getItdOdv();
		System.out.println(odv.getUsage());
		Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
		while (l.hasNext()) {
			OdvNameElemType nameElem = l.next();
			System.out.println("  " + nameElem.getLocality() + "  " + nameElem.getValue());
		}
		
		System.out.println("Serving Lines");
		Iterator<ItdServingLineType> i = result.getItdDepartureMonitorRequest().getItdServingLines().getItdServingLines().iterator();
		while (i.hasNext()) {
			ItdServingLineType line = i.next();
			System.out.println( line.getIndex()+" "+line.getTrainName()+" "+line.getDirection());
		}
		
		System.out.println("Departures");
		Iterator<ItdDepartureType> depI = result.getItdDepartureMonitorRequest().getItdDepartureList().getItdDepartures().iterator();
		while (depI.hasNext()) {
			ItdDepartureType dep = depI.next();
			System.out.println( dep.getItdServingLine().getRealtime()+" "+dep.getItdDateTime().toString()+": "+dep.getItdServingLine().getNumber()+" -> "+dep.getItdServingLine().getDirection());
		}
		
		
	}

	@Test
	public void testArrivalSingleLine() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getStationBoard(StationBoardEnum.ARRIVAL, "Karlsruhe,Jägerhaus");
		System.out.println("Version: "+result.getVersion());
		System.out.println("SessionID: "+result.getVersion());
		
		result = efaService.getStationBoardLine( result.getSessionID(), "4:0");

		ItdOdvType odv = result.getItdDepartureMonitorRequest().getItdOdv();
		System.out.println(odv.getUsage());
		Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
		while (l.hasNext()) {
			OdvNameElemType nameElem = l.next();
			System.out.println("  " + nameElem.getLocality() + "  " + nameElem.getValue());
		}
		
		System.out.println("Serving Lines");
		Iterator<ItdServingLineType> i = result.getItdDepartureMonitorRequest().getItdServingLines().getItdServingLines().iterator();
		while (i.hasNext()) {
			ItdServingLineType line = i.next();
			System.out.println( line.getIndex()+" "+line.getTrainName()+" "+line.getDirection());
		}
		
		System.out.println("Arrivals");
		Iterator<ItdArrivalType> arrI = result.getItdDepartureMonitorRequest().getItdArrivalList().getItdArrivals().iterator();
		while (arrI.hasNext()) {
			ItdArrivalType arr = arrI.next();
			System.out.println( arr.getItdServingLine().getRealtime()+" "+arr.getItdDateTime().toString()+": "+arr.getItdServingLine().getNumber()+" -> "+arr.getItdServingLine().getDirection());
		}
	}
	
}
