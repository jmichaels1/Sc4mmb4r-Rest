package com.jmsr.Sc4mmb4r.service.converter;

import java.util.Optional;

import com.jmsr.Sc4mmb4r.persistence.entity.Restaurant;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;

public class RestaurantConverter {
	
	public static Restaurant mapToEntity(final RestaurantRest restaurantRest) {
		final Restaurant restaurant = new Restaurant();
		mapToEntity(restaurantRest, restaurant);
		return restaurant;
	}
	
	public static void mapToEntity(final RestaurantRest restaurantRest, final Restaurant restaurant) {
		Optional.ofNullable(restaurantRest.getName()).ifPresent(name -> restaurant.setName(name));
		Optional.ofNullable(restaurantRest.getAddress()).ifPresent(address -> restaurant.setAddress(address));
		Optional.ofNullable(restaurantRest.getDescription()).ifPresent(description -> restaurant.setDescription(description));
		Optional.ofNullable(restaurantRest.getIsActive()).ifPresent(isActive -> restaurant.setIsActive(isActive));
	}

	public static RestaurantRest mapToRest(final Restaurant restaurant) {
		final RestaurantRest restaurantRest = new RestaurantRest();
		Optional.ofNullable(restaurant.getId()).ifPresent(id -> restaurantRest.setId(id));
		Optional.ofNullable(restaurant.getName()).ifPresent(name -> restaurantRest.setName(name));
		Optional.ofNullable(restaurant.getAddress()).ifPresent(address -> restaurantRest.setAddress(address));
		Optional.ofNullable(restaurant.getDescription()).ifPresent(description -> restaurantRest.setDescription(description));
		Optional.ofNullable(restaurant.getIsActive()).ifPresent(isActive -> restaurantRest.setIsActive(isActive));
		return restaurantRest;
	}
}
