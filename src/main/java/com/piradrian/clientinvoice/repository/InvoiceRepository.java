package com.piradrian.clientinvoice.repository;

import com.piradrian.clientinvoice.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
}
