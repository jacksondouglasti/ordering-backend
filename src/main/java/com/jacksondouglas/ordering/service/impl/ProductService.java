package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.Category;
import com.jacksondouglas.ordering.domain.Product;
import com.jacksondouglas.ordering.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.CategoryRepository;
import com.jacksondouglas.ordering.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements com.jacksondouglas.ordering.service.IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product findById(Integer id) {
        Product product = productRepository.findOne(id);

        if (product == null) {
            throw new ObjectNotFoundException("Product not found. Id" + id);
        }
        return product;
    }

    @Override
    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAll(ids);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }
}
