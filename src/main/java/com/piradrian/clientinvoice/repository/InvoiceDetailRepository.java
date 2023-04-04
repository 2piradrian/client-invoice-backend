package com.piradrian.clientinvoice.repository;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetailModel, Long> {
}
