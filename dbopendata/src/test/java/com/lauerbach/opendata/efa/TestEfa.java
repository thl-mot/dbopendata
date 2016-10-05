package com.lauerbach.opendata.efa;

import java.util.Iterator;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lauerbach.opendata.efa.DmRequest.StationBoardEnum;
import com.lauerbach.opendata.efa.xml.ItdArrivalType;
import com.lauerbach.opendata.efa.xml.ItdDepartureType;
import com.lauerbach.opendata.efa.xml.ItdOdvType;
import com.lauerbach.opendata.efa.xml.ItdRequest;
import com.lauerbach.opendata.efa.xml.ItdServingLineType;
import com.lauerbach.opendata.efa.xml.OdvNameElemType;

public class TestEfa {

	Logger log= Logger.getLogger( TestEfa.class.getName());
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println( "Logging Property "+System.getProperty("java.util.logging.config.file"));
	}
	
	@Test
	public void test() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getTrip("Karlsruhe, Jägerhaus", "Manheim");
		log.fine("Version: "+result.getVersion());

		Iterator<ItdOdvType> i = result.getItdTripRequest().getItdOdvs().iterator();
		while (i.hasNext()) {
			ItdOdvType odv = i.next();
			log.fine(odv.getUsage());
			if (odv.getUsage().equals("origin")) {
				Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
				while (l.hasNext()) {
					OdvNameElemType nameElem = l.next();
					log.fine("  " + nameElem.getLocality() + "  " + nameElem.getValueAttribute());
				}
			}
		}
	}

	@Test
	public void testDeparture() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getStationBoard(StationBoardEnum.DEPARTURE, "Karlsruhe,Jägerhaus");
		log.fine("Version: "+result.getVersion());

		ItdOdvType odv = result.getItdDepartureMonitorRequest().getItdOdv();
		log.fine("Usage: "+odv.getUsage());
		Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
		while (l.hasNext()) {
			OdvNameElemType nameElem = l.next();
			log.fine("  " + nameElem.getLocality() + "  " + nameElem.getValue());
		}

		Iterator<ItdServingLineType> i = result.getItdDepartureMonitorRequest().getItdServingLines()
				.getItdServingLines().iterator();
		while (i.hasNext()) {
			ItdServingLineType line = i.next();
			log.fine(line.getIndex() + " " + line.getTrainName() + " " + line.getDirection());
		}

	}

	@Test
	public void testDepartureSingleLine() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getStationBoard(StationBoardEnum.DEPARTURE, "Karlsruhe,Jägerhaus");
		log.fine("Version: " + result.getVersion());
		log.fine("SessionID: " + result.getVersion());

		result = efaService.getStationBoardLine(result.getSessionID(), StationBoardEnum.DEPARTURE, "4:0");

		ItdOdvType odv = result.getItdDepartureMonitorRequest().getItdOdv();
		log.fine(odv.getUsage());
		Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
		while (l.hasNext()) {
			OdvNameElemType nameElem = l.next();
			log.fine("  " + nameElem.getLocality() + "  " + nameElem.getValue());
		}

		log.fine("Serving Lines");
		Iterator<ItdServingLineType> i = result.getItdDepartureMonitorRequest().getItdServingLines()
				.getItdServingLines().iterator();
		while (i.hasNext()) {
			ItdServingLineType line = i.next();
			log.fine(line.getIndex() + " " + line.getTrainName() + " " + line.getDirection());
		}

		log.fine("Departures");
		Iterator<ItdDepartureType> depI = result.getItdDepartureMonitorRequest().getItdDepartureList()
				.getItdDepartures().iterator();
		while (depI.hasNext()) {
			ItdDepartureType dep = depI.next();
			log.fine(dep.getItdServingLine().getRealtime() + " " + dep.getItdDateTime().toString() + ": "
					+ dep.getItdServingLine().getNumber() + " -> " + dep.getItdServingLine().getDirection());
		}

	}

	@Test
	public void testArrivalSingleLine() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getStationBoard(StationBoardEnum.ARRIVAL, "Karlsruhe,Jägerhaus");
		log.fine("Version: " + result.getVersion());
		log.fine("SessionID: " + result.getVersion());

		result = efaService.getStationBoardLine(result.getSessionID(), StationBoardEnum.ARRIVAL, "4:0");

		ItdOdvType odv = result.getItdDepartureMonitorRequest().getItdOdv();
		log.fine(odv.getUsage());
		Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
		while (l.hasNext()) {
			OdvNameElemType nameElem = l.next();
			log.fine("  " + nameElem.getLocality() + "  " + nameElem.getValue());
		}

		log.fine("Serving Lines");
		Iterator<ItdServingLineType> i = result.getItdDepartureMonitorRequest().getItdServingLines()
				.getItdServingLines().iterator();
		while (i.hasNext()) {
			ItdServingLineType line = i.next();
			log.fine(line.getIndex() + " " + line.getTrainName() + " " + line.getDirection());
		}

		log.fine("Arrivals");
		Iterator<ItdArrivalType> arrI = result.getItdDepartureMonitorRequest().getItdArrivalList().getItdArrivals()
				.iterator();
		while (arrI.hasNext()) {
			ItdArrivalType arr = arrI.next();
			log.fine(arr.getItdServingLine().getRealtime() + " " + arr.getItdDateTime().toString() + ": "
					+ arr.getItdServingLine().getNumber() + " -> " + arr.getItdServingLine().getDirection());
		}
	}

}
