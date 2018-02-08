package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Purchase;
import org.springframework.data.domain.Page;

public interface IPurchaseService {
    Purchase findById(Integer id);

    Purchase save(Purchase purchase);

    Page<Purchase> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
