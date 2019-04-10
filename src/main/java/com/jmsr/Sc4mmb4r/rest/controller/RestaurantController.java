package com.jmsr.Sc4mmb4r.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;

public interface RestaurantController {

	ResponseEntity<List<RestaurantRest>> retrieveRestaurants();

	ResponseEntity<RestaurantRest> retrieveRestaurant(Long restaurantId) throws Exception;

	ResponseEntity<RestaurantRest> createRestaurant(RestaurantRest restaurantRest) throws Exception;

	ResponseEntity<RestaurantRest> updateRestaurant(Long restaurantId, RestaurantRest restaurantRest) throws Exception;

}
