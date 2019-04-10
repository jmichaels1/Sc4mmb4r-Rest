package com.jmsr.Sc4mmb4r.service.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jmsr.Sc4mmb4r.persistence.entity.Restaurant;
import com.jmsr.Sc4mmb4r.persistence.repository.RestaurantRepository;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;
import com.jmsr.Sc4mmb4r.service.impl.RestaurantServiceImpl;

public class RestaurantServiceImplTest {

	private static final Long ID_1 = 1L;
	private static final Long ID_BAD = 0L;
	private static final int INDEX_FIRST = 0;
	private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	private static final String RESTAURANT_REST_NAME = "Goiko";
	private static final String RESTAURANT_REST_ADDRESS = "Avinguda Diagonal 123";
	private static final String RESTAURANT_REST_DESCRIPTION = "Las mejores burguer";
	private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final String RESTAURANT_NAME = "Chino";
	private static final String RESTAURANT_ADDRESS = "Avinguda Sant Pere 458";
	private static final String RESTAURANT_DESCRIPTION = "Las mejores Cervezas";
	private static final List<Restaurant> RESTAURANT_LIST = new ArrayList<>();
	private static final String RESTAURANT_NAME_TO_UPDATE = "Suamu";
	private static final String RESTAURANT_NAME_BAD = "Restaurant name bad";

	@Mock
	private RestaurantRepository restaurantRepository;

	@InjectMocks
	private RestaurantServiceImpl restaurantServiceImpl;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

		RESTAURANT_REST_LIST.clear();
		RESTAURANT_LIST.clear();

		RESTAURANT_REST.setName(RESTAURANT_REST_NAME);
		RESTAURANT_REST.setAddress(RESTAURANT_REST_ADDRESS);
		RESTAURANT_REST.setDescription(RESTAURANT_REST_DESCRIPTION);
		RESTAURANT_REST.setIsActive(Boolean.TRUE);
		RESTAURANT_REST_LIST.add(RESTAURANT_REST);

		RESTAURANT.setId(ID_1);
		RESTAURANT.setName(RESTAURANT_NAME);
		RESTAURANT.setAddress(RESTAURANT_ADDRESS);
		RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
		RESTAURANT.setIsActive(Boolean.TRUE);
		RESTAURANT_LIST.add(RESTAURANT);

		when(restaurantRepository.findByNameAndIsActiveTrue(RESTAURANT_REST_NAME)).thenReturn(null);
		when(restaurantRepository.findAll()).thenReturn(RESTAURANT_LIST);
		when(restaurantRepository.findById(ID_1)).thenReturn(Optional.ofNullable(RESTAURANT));
		when(restaurantRepository.findByNameAndIsActiveTrue(RESTAURANT_NAME_BAD)).thenReturn(new Restaurant());
	}

	@Test
	public void testCreateRestaurant() throws Exception {
		RESTAURANT_REST.setId(null);
		final RestaurantRest restaurantResult = restaurantServiceImpl.createRestaurant(RESTAURANT_REST);
		assertNotNull(restaurantResult);
		assertEquals(RESTAURANT_REST_NAME, restaurantResult.getName());
	}

	@Test(expected = Exception.class)
	public void testUpdateRestaurantIdNullException() throws Exception {
		RESTAURANT_REST.setId(ID_1);
		restaurantServiceImpl.createRestaurant(RESTAURANT_REST);
	}

	@Test(expected = Exception.class)
	public void testUpdateRestaurantNameNullException() throws Exception {
		RESTAURANT_REST.setName(null);
		restaurantServiceImpl.createRestaurant(RESTAURANT_REST);
	}

	@Test
	public void testRetrieveRestaurants() {
		final List<RestaurantRest> restaurantRestListResult = restaurantServiceImpl.retrieveRestaurants();
		assertNotNull(restaurantRestListResult);
		assertEquals(RESTAURANT_NAME, restaurantRestListResult.get(INDEX_FIRST).getName());
		assertEquals(ID_1, restaurantRestListResult.get(INDEX_FIRST).getId());
	}

	@Test
	public void testRetrieveRestaurant() throws Exception {
		final RestaurantRest restaurantRestResult = restaurantServiceImpl.retrieveRestaurant(ID_1);
		assertNotNull(restaurantRestResult);
		assertEquals(RESTAURANT_NAME, restaurantRestResult.getName());
	}

	@Test(expected = Exception.class)
	public void testRetrieveRestaurantException() throws Exception {
		restaurantServiceImpl.retrieveRestaurant(ID_BAD);
	}

	@Test
	public void testUpdateRestaurant() throws Exception {
		RESTAURANT_REST.setId(ID_1);
		RESTAURANT_REST.setName(RESTAURANT_NAME_TO_UPDATE);
		final RestaurantRest restaurantRestResult = restaurantServiceImpl.updateRestaurant(ID_1, RESTAURANT_REST);
		assertEquals(RESTAURANT_NAME_TO_UPDATE, restaurantRestResult.getName());
	}

	@Test(expected = Exception.class)
	public void testUpdateRestaurantExceptionId() throws Exception {
		restaurantServiceImpl.updateRestaurant(ID_BAD, RESTAURANT_REST);
	}

	@Test(expected = Exception.class)
	public void testUpdateRestaurantException() throws Exception {
		RESTAURANT_REST.setId(ID_BAD);
		restaurantServiceImpl.updateRestaurant(ID_BAD, RESTAURANT_REST);
	}

	@Test(expected = Exception.class)
	public void testUpdateRestaurantNameException() throws Exception {
		RESTAURANT_REST.setId(ID_1);
		RESTAURANT_REST.setName(RESTAURANT_NAME_BAD);
		restaurantServiceImpl.updateRestaurant(ID_1, RESTAURANT_REST);
	}
}
