package com.piradrian.clientinvoice.validation;

import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ProductValidation {
    private final ProductRepository productRepository;

    public ProductValidation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createValidation(ProductModel product) throws Exception {
        Optional<ProductModel> optionalProduct = productRepository.findByCode(product.getCode());

        hasInvalidValues(product);

        if(optionalProduct.isPresent()){
            throw new Exception("El código ya está en uso");
        }
    }

    public void updateValidation(ProductModel product) throws Exception {
        Optional<ProductModel> optionalProduct = productRepository.findById(product.getId());

        hasInvalidValues(product);

        if (product.getId() <= 0){
            throw new Exception("El id no es válido");
        }
        if(optionalProduct.isEmpty()){
            throw new Exception("El producto que intenta modificar no existe");
        }

        Optional<ProductModel> optionalProductWithCode = productRepository.findByCode(product.getCode());

        if (optionalProductWithCode.isPresent() && !optionalProductWithCode.get().getId().equals(product.getId())) {
            throw new Exception("El código ya está en uso");
        }

    }

    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public void deleteValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public static void hasInvalidValues (ProductModel product) throws Exception {

        if (product.getCode() == null || product.getCode().isEmpty()) {
            throw new Exception("El nombre del cliente no puede estar vacío.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new Exception("El apellido del cliente no puede estar vacío.");
        }
        if (product.getStock() <= 0) {
            throw new Exception("El stock es inválido.");
        }
        if(product.getPrice() <= 0){
            throw new Exception("El precio es inválido.");
        }
    }

}
