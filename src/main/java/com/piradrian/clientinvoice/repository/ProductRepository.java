package com.piradrian.clientinvoice.repository;

import com.piradrian.clientinvoice.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByCode(String code);
}
