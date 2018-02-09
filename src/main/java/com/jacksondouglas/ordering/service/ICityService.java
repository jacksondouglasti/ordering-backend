package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.City;

import java.util.List;

public interface ICityService {

    List<City> findByState(Integer stateId);
}
