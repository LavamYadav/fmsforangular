package com.fms.repository;

import org.springframework.data.repository.CrudRepository;
//repository that extends CrudRepository

import com.fms.dto.Airport;

public interface AirportRepository extends CrudRepository<Airport, String> {
}
