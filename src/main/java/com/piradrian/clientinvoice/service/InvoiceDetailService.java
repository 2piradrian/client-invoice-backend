package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailService {

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailModel create(InvoiceDetailModel invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public InvoiceDetailModel update(InvoiceDetailModel invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public InvoiceDetailModel findById(Long id) {
        Optional<InvoiceDetailModel> invoiceDetail = invoiceDetailRepository.findById(id);
        return invoiceDetail.orElse(null);
    }

    public List<InvoiceDetailModel> findAll() {
        return invoiceDetailRepository.findAll();
    }

    public void delete(Long id) {
        invoiceDetailRepository.deleteById(id);
    }
}
