package com.piradrian.clientinvoice.repository;

import com.piradrian.clientinvoice.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
