package com.jmsr.Sc4mmb4r.service.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.jmsr.Sc4mmb4r.persistence.entity.Restaurant;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;

public class RestaurantConverterTest {
	
	private static final Long ID = 1L;
	private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	private static final String RESTAURANT_REST_NAME = "Goiko";
	private static final String RESTAURANT_REST_ADDRESS = "Avinguda Diagonal 123";
	private static final String RESTAURANT_REST_DESCRIPTION = "Las mejores burguer";
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final String RESTAURANT_NAME = "Chino";
	private static final String RESTAURANT_ADDRESS = "Avinguda Sant Pere 458";
	private static final String RESTAURANT_DESCRIPTION = "Las mejores Cervezas";
	
	
	@Before
	public void init()  {
		MockitoAnnotations.initMocks(this);
		
		RESTAURANT_REST.setName(RESTAURANT_REST_NAME);
		RESTAURANT_REST.setAddress(RESTAURANT_REST_ADDRESS);
		RESTAURANT_REST.setDescription(RESTAURANT_REST_DESCRIPTION);
		RESTAURANT_REST.setIsActive(Boolean.TRUE);
		
		RESTAURANT.setId(ID);
		RESTAURANT.setName(RESTAURANT_NAME);
		RESTAURANT.setAddress(RESTAURANT_ADDRESS);
		RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
		RESTAURANT.setIsActive(Boolean.TRUE);
	}

	
	@Test
	public void testMapToEntityByCreate() {
		final Restaurant restaurantResult = RestaurantConverter.mapToEntity(RESTAURANT_REST);
		assertNotNull(restaurantResult);
		assertEquals(RESTAURANT_REST_NAME, restaurantResult.getName());
		assertEquals(RESTAURANT_REST_ADDRESS, restaurantResult.getAddress());
		assertEquals(RESTAURANT_REST_DESCRIPTION, restaurantResult.getDescription());
		assertEquals(Boolean.TRUE, restaurantResult.getIsActive());
	}
	
	@Test
	public void testMapToEntityByUpdate() {
		RestaurantConverter.mapToEntity(RESTAURANT_REST, RESTAURANT);
		assertNotNull(RESTAURANT);
		assertEquals(RESTAURANT_REST_NAME, RESTAURANT.getName());
		assertEquals(RESTAURANT_REST_ADDRESS, RESTAURANT.getAddress());
		assertEquals(RESTAURANT_REST_DESCRIPTION, RESTAURANT.getDescription());
		assertEquals(Boolean.TRUE, RESTAURANT.getIsActive());
	}
	
	@Test
	public void testMapToRest() {
		final RestaurantRest restaurantRestResult = RestaurantConverter.mapToRest(RESTAURANT);
		assertNotNull(restaurantRestResult);
		assertEquals(RESTAURANT_NAME, restaurantRestResult.getName());
		assertEquals(RESTAURANT_ADDRESS, restaurantRestResult.getAddress());
		assertEquals(RESTAURANT_DESCRIPTION, restaurantRestResult.getDescription());
		assertEquals(Boolean.TRUE, restaurantRestResult.getIsActive());
	}

}
