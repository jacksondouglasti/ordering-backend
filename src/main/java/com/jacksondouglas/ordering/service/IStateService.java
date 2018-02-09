package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.State;

import java.util.List;

public interface IStateService {

    List<State> findAll();
}
