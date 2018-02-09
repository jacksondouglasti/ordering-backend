package com.jacksondouglas.ordering.controller;

import com.jacksondouglas.ordering.domain.State;
import com.jacksondouglas.ordering.dto.CityDTO;
import com.jacksondouglas.ordering.dto.StateDTO;
import com.jacksondouglas.ordering.service.ICityService;
import com.jacksondouglas.ordering.service.IStateService;
import com.jacksondouglas.ordering.service.impl.CityService;
import com.jacksondouglas.ordering.service.impl.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateController {

    @Autowired
    private IStateService stateService;

    @Autowired
    private ICityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll() {
        List<StateDTO> dtoList = stateService.findAll().stream().map(s -> new StateDTO(s)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
        List<CityDTO> dtoList = cityService.findByState(stateId).stream().map(c -> new CityDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }
}
