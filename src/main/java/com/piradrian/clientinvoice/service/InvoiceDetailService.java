package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.repository.InvoiceDetailRepository;
import com.piradrian.clientinvoice.validation.InvoiceDetailValidation;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailService {

    @Autowired
    private InvoiceDetailValidation invoiceDetailValidation;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailModel create(InvoiceDetailModel invoiceDetail) throws Exception {
        invoiceDetailValidation.createValidation(invoiceDetail);
        invoiceDetail.setPrice(invoiceDetail.getAmount() * invoiceDetail.getProductModel().getPrice()); // calculo del precio
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public InvoiceDetailModel create(List<InvoiceDetailModel> invoiceDetailList) throws Exception {
        for(InvoiceDetailModel invoiceDetail : invoiceDetailList) {
            invoiceDetailValidation.createValidation(invoiceDetail);
            invoiceDetail.setPrice(invoiceDetail.getAmount() * invoiceDetail.getProductModel().getPrice());
            invoiceDetailRepository.save(invoiceDetail);
        }
        return null;
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
