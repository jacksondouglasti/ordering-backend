package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    Product findById(Integer id);

    Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
}
