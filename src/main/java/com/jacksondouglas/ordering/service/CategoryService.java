package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Category;
import com.jacksondouglas.ordering.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(Integer id) {
        Category category = categoryRepository.findOne(id);

        if (category == null) {
            throw new ObjectNotFoundException("Category not found. Id: " + id);
        }
        return category;
    }
}
