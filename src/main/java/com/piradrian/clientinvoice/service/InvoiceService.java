package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceModel create(InvoiceModel invoice) {
        return invoiceRepository.save(invoice);
    }

    public InvoiceModel update(InvoiceModel invoice) {
        return invoiceRepository.save(invoice);
    }

    public InvoiceModel findById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<InvoiceModel> findAll() {
        return invoiceRepository.findAll();
    }

    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }
}
