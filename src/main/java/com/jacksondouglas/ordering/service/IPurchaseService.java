package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Purchase;

public interface IPurchaseService {
    Purchase findById(Integer id);

    Purchase save(Purchase purchase);
}
