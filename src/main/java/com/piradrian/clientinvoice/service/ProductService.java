package com.piradrian.clientinvoice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.repository.ProductRepository;
import com.piradrian.clientinvoice.validation.ProductValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ProductService {

    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private ProductRepository productRepository;

    public ProductModel create(ProductModel product) throws Exception {
        productValidation.createValidation(product);
        return productRepository.save(product);
    }

    public List<ProductModel> create(List<ProductModel> productList) throws Exception {
        List<ProductModel> addedProducts = new ArrayList<>();
        for(ProductModel product : productList) {
            productValidation.createValidation(product);
            addedProducts.add(productRepository.save(product));
        }
        return addedProducts;
    }

    public ProductModel update(ProductModel product) throws Exception {
        productValidation.updateValidation(product);
        return productRepository.save(product);
    }

    public ProductModel findById(Long id) throws Exception {
        productValidation.findByIdValidation(id);
        return productRepository.findById(id).orElse(null);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) throws Exception {
        productValidation.deleteValidation(id);
        productRepository.deleteById(id);
    }
}
