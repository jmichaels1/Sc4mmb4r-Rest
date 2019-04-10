package com.jmsr.Sc4mmb4r.rest.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.jmsr.Sc4mmb4r.rest.controller.RestaurantController;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;
import com.jmsr.Sc4mmb4r.service.RestaurantService;
import com.jmsr.Sc4mmb4r.utils.ScummbarConstants;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ScummbarConstants.APPLICATION_NAME + ScummbarConstants.API_VERSION_1)
public class RestaurantControllerImpl implements RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = ScummbarConstants.RESOURCE_RESTAURANTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RestaurantRest>> retrieveRestaurants() {
		return new ResponseEntity<>(restaurantService.retrieveRestaurants(), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = ScummbarConstants.RESOURCE_RESTAURANT + "/{" + ScummbarConstants.PARAMETER_RESTAURANT_ID
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantRest> retrieveRestaurant(
			@ApiParam(value = ScummbarConstants.PARAMETER_RESTAURANT_ID, required = true) @PathVariable(ScummbarConstants.PARAMETER_RESTAURANT_ID) final Long restaurantId)
			throws Exception {
		return new ResponseEntity<>(restaurantService.retrieveRestaurant(restaurantId), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = ScummbarConstants.RESOURCE_RESTAURANT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantRest> createRestaurant(
			@ApiParam(value = ScummbarConstants.RESOURCE_RESTAURANT, required = true) @Valid @RequestBody final RestaurantRest restaurantRest)
			throws Exception {
		return new ResponseEntity<>(restaurantService.createRestaurant(restaurantRest), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = ScummbarConstants.RESOURCE_RESTAURANT + "/{" + ScummbarConstants.PARAMETER_RESTAURANT_ID
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantRest> updateRestaurant(
			@ApiParam(value = ScummbarConstants.PARAMETER_RESTAURANT_ID, required = true) @PathVariable(ScummbarConstants.PARAMETER_RESTAURANT_ID) final Long restaurantId,
			@ApiParam(value = ScummbarConstants.PARAMETER_RESTAURANT, required = true) @Valid @RequestBody final RestaurantRest restaurantRest) throws Exception {
		return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantId, restaurantRest), HttpStatus.OK);
	}

}
