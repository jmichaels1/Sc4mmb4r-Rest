package com.jmsr.Sc4mmb4r.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jmsr.Sc4mmb4r.persistence.entity.Restaurant;
import com.jmsr.Sc4mmb4r.persistence.repository.RestaurantRepository;
import com.jmsr.Sc4mmb4r.rest.entity.RestaurantRest;
import com.jmsr.Sc4mmb4r.service.RestaurantService;
import com.jmsr.Sc4mmb4r.service.converter.RestaurantConverter;
import com.jmsr.Sc4mmb4r.utils.ScummbarConstants;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private static Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepository;

	public RestaurantRest createRestaurant(final RestaurantRest restaurantRest) throws Exception {
		return saveRestaurant(checkAndMapRestaurant(restaurantRest));
	}

	public List<RestaurantRest> retrieveRestaurants() {
		final List<Restaurant> restaurantRestList = restaurantRepository.findAll();
		return !CollectionUtils.isEmpty(restaurantRestList) ? restaurantRestList.stream()
				.map(restaurant -> RestaurantConverter.mapToRest(restaurant)).collect(Collectors.toList())
				: new ArrayList<>();
	}

	public RestaurantRest retrieveRestaurant(final Long restaurantId) throws Exception {
		return RestaurantConverter.mapToRest(checkFindRestaurant(restaurantId));
	}

	public RestaurantRest updateRestaurant(final Long restaurantId, final RestaurantRest restaurantRest)
			throws Exception {
		checkIdEquality(restaurantId, restaurantRest);
		Restaurant restaurantInDb = checkFindRestaurant(restaurantId);
		checkAndMapRestaurantUpdate(restaurantInDb, restaurantRest);
		return updateAndSaveRestaurant(restaurantInDb, restaurantRest);
	}

	private Restaurant checkAndMapRestaurant(final RestaurantRest restaurantRest) throws Exception {
		checkIdIfIsNotNull(restaurantRest);
		checkNameIfIsNotNullOrEmpty(restaurantRest);
		checkNameAlReadyUse(restaurantRest.getName());
		return RestaurantConverter.mapToEntity(restaurantRest);
	}

	private RestaurantRest saveRestaurant(Restaurant restaurant) throws Exception {
		try {
			restaurantRepository.save(restaurant);
			return RestaurantConverter.mapToRest(restaurant);
		} catch (final Exception e) {
			LOGGER.error(ScummbarConstants.INTERNAL_SERVER_ERROR, e);
			throw new Exception(ScummbarConstants.INTERNAL_SERVER_ERROR);
		}
	}

	private void checkIdIfIsNotNull(final RestaurantRest restaurantRest) throws Exception {
		if (restaurantRest.getId() != null) {
			LOGGER.warn(ScummbarConstants.INVALID_RESTAURANT);
			throw new Exception(ScummbarConstants.INVALID_RESTAURANT);
		}
	}

	private void checkNameIfIsNotNullOrEmpty(RestaurantRest restaurantRest) throws Exception {
		if (StringUtils.isBlank(restaurantRest.getName())) {
			LOGGER.warn(ScummbarConstants.RESTAURANT_NAME_IS_NECESSARY);
			throw new Exception(ScummbarConstants.RESTAURANT_NAME_IS_NECESSARY);
		}
	}

	private void checkNameAlReadyUse(String restaurantName) throws Exception {
		Restaurant restaurant = restaurantRepository.findByNameAndIsActiveTrue(restaurantName);
		if (restaurant != null) {
			LOGGER.warn(ScummbarConstants.RESTAURANT_ALREADY_USE);
			throw new Exception(ScummbarConstants.RESTAURANT_ALREADY_USE);
		}
	}

	private Restaurant checkFindRestaurant(final Long restaurantId) throws Exception {
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
		if (!restaurantOptional.isPresent()) {
			LOGGER.warn(ScummbarConstants.RESTAURANT_NOT_FOUND);
			throw new Exception(ScummbarConstants.RESTAURANT_NOT_FOUND);
		}
		return restaurantOptional.get();
	}

	private void checkIdEquality(final Long restaurantId, final RestaurantRest restaurantRest) throws Exception {
		if (!restaurantId.equals(restaurantRest.getId())) {
			LOGGER.warn(ScummbarConstants.ID_NOT_EQUALS);
			throw new Exception(ScummbarConstants.ID_NOT_EQUALS);
		}
	}

	private void checkAndMapRestaurantUpdate(final Restaurant restaurantInDb, final RestaurantRest restaurantRest)
			throws Exception {
		checkNameUpdate(restaurantInDb, restaurantRest);
		RestaurantConverter.mapToEntity(restaurantRest, restaurantInDb);
	}

	private void checkNameUpdate(Restaurant restaurantInDb, RestaurantRest restaurantRest) throws Exception {
		if (StringUtils.isNotBlank(restaurantRest.getName())
				&& !restaurantRest.getName().equals(restaurantInDb.getName())
				|| restaurantRest.getIsActive() != null && restaurantRest.getIsActive()
						&& !restaurantInDb.getIsActive()) {
			final String restaurantName = restaurantRest.getName() != null ? restaurantRest.getName()
					: restaurantInDb.getName();
			checkNameAlReadyUse(restaurantName);
		}
	}

	private RestaurantRest updateAndSaveRestaurant(final Restaurant restaurantInDb, final RestaurantRest restaurantRest)
			throws Exception {
		// TODO validar si se desactiva para desactivar mesas y turno relacionados
		return saveRestaurant(restaurantInDb);
	}
}
