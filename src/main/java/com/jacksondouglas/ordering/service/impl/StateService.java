package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.State;
import com.jacksondouglas.ordering.repository.StateRepository;
import com.jacksondouglas.ordering.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService implements IStateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> findAll() {
        return stateRepository.findAllByOrderByName();
    }
}
