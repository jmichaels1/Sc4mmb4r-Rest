package com.jmsr.Sc4mmb4r.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.jmsr.Sc4mmb4r.rest.controller.impl.RestaurantControllerImpl;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;
import com.jmsr.Sc4mmb4r.service.RestaurantService;

public class RestaurantControllerTest {

	private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	private static final Long ID_1 = 1L;
	private static final int INDEX_FIRST = 0;
	private static final String RESTAURANT_REST_NAME = "Goiko";
	private static final String RESTAURANT_REST_ADDRESS = "Avinguda Diagonal 123";
	private static final String RESTAURANT_REST_DESCRIPTION = "Las mejores burguer";
	private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();

	@Mock
	private RestaurantService restaurantService;

	@InjectMocks
	private RestaurantControllerImpl restaurantControllerImpl;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

		RESTAURANT_REST.setId(ID_1);
		RESTAURANT_REST.setName(RESTAURANT_REST_NAME);
		RESTAURANT_REST.setAddress(RESTAURANT_REST_ADDRESS);
		RESTAURANT_REST.setDescription(RESTAURANT_REST_DESCRIPTION);
		RESTAURANT_REST.setIsActive(Boolean.TRUE);
		RESTAURANT_REST_LIST.add(RESTAURANT_REST);

		when(restaurantService.retrieveRestaurants()).thenReturn(RESTAURANT_REST_LIST);
		when(restaurantService.retrieveRestaurant(ID_1)).thenReturn(RESTAURANT_REST);
		when(restaurantService.createRestaurant(RESTAURANT_REST)).thenReturn(RESTAURANT_REST);
		when(restaurantService.updateRestaurant(ID_1, RESTAURANT_REST)).thenReturn(RESTAURANT_REST);
	}

	@Test
	public void testRetrieveRestaurants() {
		final ResponseEntity<List<RestaurantRest>> restaurantResult = restaurantControllerImpl.retrieveRestaurants();
		assertNotNull(restaurantResult);
		assertEquals(RESTAURANT_REST.getName(), restaurantResult.getBody().get(INDEX_FIRST).getName());
	}

	@Test
	public void testRetrieveRestaurant() throws Exception {
		final ResponseEntity<RestaurantRest> restaurantResult = restaurantControllerImpl.retrieveRestaurant(ID_1);
		assertNotNull(restaurantResult);
		assertEquals(RESTAURANT_REST.getDescription(), restaurantResult.getBody().getDescription());
	}

	@Test
	public void testCreateRestaurant() throws Exception {
		RESTAURANT_REST.setId(null);
		final ResponseEntity<RestaurantRest> restaurantResult = restaurantControllerImpl
				.createRestaurant(RESTAURANT_REST);
		assertNotNull(restaurantResult.getBody());
		assertEquals(RESTAURANT_REST.getIsActive(), restaurantResult.getBody().getIsActive());
	}

	@Test
	public void testUpdateRestaurant() throws Exception {
		final ResponseEntity<RestaurantRest> restaurantResult = restaurantControllerImpl.updateRestaurant(ID_1,
				RESTAURANT_REST);
		assertNotNull(restaurantResult.getBody());
		assertEquals(RESTAURANT_REST.getName(), restaurantResult.getBody().getName());
	}
}
