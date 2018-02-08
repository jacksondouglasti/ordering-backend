package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.Category;
import com.jacksondouglas.ordering.dto.CategoryDTO;
import com.jacksondouglas.ordering.service.exception.DataIntegrityException;
import com.jacksondouglas.ordering.service.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements com.jacksondouglas.ordering.service.ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Integer id) {
        Category category = categoryRepository.findOne(id);

        if (category == null) {
            throw new ObjectNotFoundException("Category not found. Id: " + id);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public Category save(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category curr = findById(category.getId());
        curr.setName(category.getName());
        return categoryRepository.save(curr);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        try {
            categoryRepository.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("It's not possible to delete a Category that has Products.");
        }
    }

    @Override
    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}