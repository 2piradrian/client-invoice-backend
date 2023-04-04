package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel create(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel update(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel findById(Long id) {
        Optional<ProductModel> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
