package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.City;
import com.jacksondouglas.ordering.repository.CityRepository;
import com.jacksondouglas.ordering.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findByState(Integer stateId) {
        return cityRepository.findByStateId(stateId);
    }
}
