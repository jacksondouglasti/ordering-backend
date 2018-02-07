package com.jacksondouglas.ordering.controllers;

import com.jacksondouglas.ordering.controllers.utils.URL;
import com.jacksondouglas.ordering.domain.Product;
import com.jacksondouglas.ordering.dto.ProductDTO;
import com.jacksondouglas.ordering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product purchase = productService.findById(id);
        return ResponseEntity.ok(purchase);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "0") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Product> products = productService.search(URL.decodeParam(name), URL.decodeIntegerList(categories), page, linesPerPage, orderBy, direction);
        Page<ProductDTO> productsDTO = products.map(c -> new ProductDTO(c));
        return ResponseEntity.ok(productsDTO);
    }
}
