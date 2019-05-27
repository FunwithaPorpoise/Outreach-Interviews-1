package com.outreach.interviews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.outreach.interviews.map.builder.MapRoutesHelper;
import com.outreach.interviews.map.enums.MapOperations;
import com.outreach.interviews.map.enums.MapRegions;

public class TestMapRoutesHelper 
{	
	
	@Test
	public void testMapRoutesHelperApiKey1() throws UnsupportedOperationException, IOException {
		new MapRoutesHelper.RoutesBuilder()
			.setOrigin("Sudbury")
			.setDestination("Ottawa")
			.setRegion(MapRegions.en)
			.setURL(MapOperations.directions)
			.build();
	}
	
	@Test
	public void testMapRoutesHelperApiKey2() throws UnsupportedOperationException, IOException {
		List<String> steps = new MapRoutesHelper.RoutesBuilder()
			.setOrigin("Sudbury")
			.setDestination("Ottawa")
			.setRegion(MapRegions.en)
			.setURL(MapOperations.directions)
			.build()
			.getDirections();
		
		assertNotNull(steps);
		assertTrue(steps.size() > 5);
	}
	
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testMapRoutesHelperApiKey4() throws UnsupportedOperationException, IOException {
		List<String> steps = new MapRoutesHelper.RoutesBuilder()
			.setDestination("Ottawa")
			.setRegion(MapRegions.en)
			.setURL(MapOperations.directions)
			.build()
			.getDirections();
		
		assertNotNull(steps);
	}
	
	@Test
	public void testMapRoutesHelperApiKey5() throws UnsupportedOperationException, IOException {
		List<String> steps = new MapRoutesHelper.RoutesBuilder()
			.setOrigin("Sudbury")
			.setDestination("Ottawa")
			.setRegion(MapRegions.en)
			.setURL(MapOperations.directions)
			.build()
			.getDirections();
		
		assertNotNull(steps);
	}
	
	@Test
	public void testMapGeocode() throws UnsupportedOperationException, IOException {
		JsonObject geocodes = new MapRoutesHelper.RoutesBuilder()
				.setOrigin("Sudbury")
				.setRegion(MapRegions.en)
				.setURL(MapOperations.geocode)
				.build()
				.getGeocodes();
		
		assertNotNull(geocodes);
		assertEquals(46.4917317, geocodes.get("lat").getAsDouble(), 0.1);
		assertEquals(-80.9930299, geocodes.get("lng").getAsDouble(), 0.1);
	}
	
	@Test
	public void testMapGeocode2() throws UnsupportedOperationException, IOException {
		JsonObject geocodes = new MapRoutesHelper.RoutesBuilder()
				.setOrigin("Ottawa")
				.setRegion(MapRegions.en)
				.setURL(MapOperations.geocode)
				.build()
				.getGeocodes();
		
		assertNotNull(geocodes);
		assertEquals(45.4215296, geocodes.get("lat").getAsDouble(), 0.1);
		assertEquals(-75.6971931, geocodes.get("lng").getAsDouble(), 0.1);
	}
	
}
