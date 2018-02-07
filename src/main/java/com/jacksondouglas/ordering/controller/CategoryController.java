package com.jacksondouglas.ordering.controller;

import com.jacksondouglas.ordering.domain.Category;
import com.jacksondouglas.ordering.dto.CategoryDTO;
import com.jacksondouglas.ordering.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categoriesDTO = categoryService.findAll().stream().map(c -> new CategoryDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok(categoriesDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Category> categories = categoryService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> categoriesDTO = categories.map(c -> new CategoryDTO(c));
        return ResponseEntity.ok(categoriesDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.save(categoryService.fromDTO(categoryDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer id) {
        categoryDTO.setId(id);
        categoryService.update(categoryService.fromDTO(categoryDTO));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
