package com.jmsr.Sc4mmb4r.service;

import java.util.List;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;

public interface RestaurantService {

	RestaurantRest createRestaurant(RestaurantRest restaurantRest) throws Exception;

	List<RestaurantRest> retrieveRestaurants();

	RestaurantRest retrieveRestaurant(Long restaurantId) throws Exception;

	RestaurantRest updateRestaurant(Long restaurantId, RestaurantRest restaurantRest) throws Exception;

}
