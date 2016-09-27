package com.lauerbach.opendata.efa;

import java.util.Iterator;

import org.junit.Test;

import com.lauerbach.opendata.efa.xml.ItdOdvType;
import com.lauerbach.opendata.efa.xml.ItdRequest;
import com.lauerbach.opendata.efa.xml.OdvNameElemType;

public class TestEfa {

	@Test
	public void test() throws Exception {
		EFAService efaService = new EFAService();
		ItdRequest result = efaService.getTrip("Karlsruhe", "Manheim");
		System.out.println(result);
		System.out.println(result.getVersion());

		Iterator<ItdOdvType> i = result.getItdTripRequest().getItdOdvs().iterator();
		while (i.hasNext()) {
			ItdOdvType odv = i.next();
			System.out.println( odv.getUsage());
			if (odv.getUsage().equals("origin")) {
				Iterator<OdvNameElemType> l = odv.getItdOdvName().getOdvNameElems().iterator();
				while (l.hasNext()) {
					OdvNameElemType nameElem = l.next();
					System.out.println("  "+nameElem.getLocality()+"  "+nameElem.getValueAttribute());
				}
			}
		}
	}

}
