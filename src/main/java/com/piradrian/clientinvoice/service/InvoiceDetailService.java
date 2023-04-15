package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.repository.InvoiceDetailRepository;
import com.piradrian.clientinvoice.validation.InvoiceDetailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {

    @Autowired
    private InvoiceDetailValidation invoiceDetailValidation;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public void create(InvoiceDetailModel invoiceDetail, InvoiceModel invoiceCreated) throws Exception {
        invoiceDetail.setInvoiceModel(invoiceCreated);
        invoiceDetailValidation.createValidation(invoiceDetail);
        invoiceDetail.setPrice(invoiceDetail.getAmount() * invoiceDetail.getProductModel().getPrice());
        invoiceDetailRepository.save(invoiceDetail);
    }

    public InvoiceDetailModel update(InvoiceDetailModel invoiceDetail) throws Exception {
        invoiceDetailValidation.updateValidation(invoiceDetail);
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public InvoiceDetailModel findById(Long id) throws Exception {
        invoiceDetailValidation.findByIdValidation(id);
        return invoiceDetailRepository.findById(id).orElse(null);
    }

    public List<InvoiceDetailModel> findAll() {
        return invoiceDetailRepository.findAll();
    }

}