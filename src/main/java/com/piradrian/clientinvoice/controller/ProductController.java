package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/")
    public ResponseEntity<ProductModel> create(@RequestBody ProductModel product) throws Exception {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }
    @PostMapping(path = "/list/")
    public ResponseEntity<List<ProductModel>> create(@RequestBody List<ProductModel> productList) throws Exception {
        return new ResponseEntity<>(productService.create(productList), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<ProductModel> update(@RequestBody ProductModel product) throws Exception {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ProductModel>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
