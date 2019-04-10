package com.jmsr.Sc4mmb4r.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmsr.Sc4mmb4r.persistence.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	Restaurant findByNameAndIsActiveTrue(String name);

}
