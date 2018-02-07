package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Category;
import com.jacksondouglas.ordering.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Category findById(Integer id);

    List<Category> findAll();

    Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

    Category save(Category category);

    Category update(Category category);

    void delete(Integer id);

    Category fromDTO(CategoryDTO categoryDTO);
}
