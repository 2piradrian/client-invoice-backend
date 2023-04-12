package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.repository.InvoiceDetailRepository;
import com.piradrian.clientinvoice.repository.InvoiceRepository;
import com.piradrian.clientinvoice.validation.InvoiceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceValidation invoiceValidation;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceModel create(InvoiceModel invoice) throws Exception {
        invoiceValidation.createValidation(invoice);
        return invoiceRepository.save(invoice);
    }

    public InvoiceModel findById(Long id) throws Exception {
        invoiceValidation.findByIdValidation(id);
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<InvoiceModel> findAll() {
        return invoiceRepository.findAll();
    }
}
