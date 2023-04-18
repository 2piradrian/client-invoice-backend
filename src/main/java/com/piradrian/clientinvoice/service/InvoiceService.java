package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.repository.InvoiceRepository;
import com.piradrian.clientinvoice.repository.ProductRepository;
import com.piradrian.clientinvoice.validation.InvoiceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceValidation invoiceValidation;
    @Autowired
    private InvoiceDetailService invoiceDetailService;

    public List<InvoiceModel> create(List<InvoiceDetailModel> invoiceDetailList, ClientModel client) throws Exception {
        // lista para dar feedback al frontend
       List<InvoiceModel> addedInvoices = new ArrayList<>();
        // contador de precio
       double total = 0;
       // recorre la lista de detalles de factura para validar
       for(InvoiceDetailModel invoiceDetail : invoiceDetailList) {
             Optional<ProductModel> paramProduct = productRepository.findById(invoiceDetail.getProductModel().getId());
             invoiceValidation.existsByIdValidation(paramProduct);
             invoiceDetail.setProductModel(paramProduct.get());
             // suma el precio del producto por la cantidad
             total += invoiceDetail.getAmount() * invoiceDetail.getProductModel().getPrice();
       }
       // crea la factura
       InvoiceModel invoice = new InvoiceModel();
       invoice.setClientModel(client);
       invoice.setTotal(total);
       // valida la factura
       invoiceValidation.createValidation(client);
       InvoiceModel invoiceCreated = invoiceRepository.save(invoice);
       // recorre la lista de detalles de factura para crearlos
       for(InvoiceDetailModel invoiceDetail : invoiceDetailList) {
           invoiceDetailService.create(invoiceDetail, invoiceCreated);
           addedInvoices.add(invoiceCreated);
       }

       return addedInvoices;
    }

    public InvoiceModel findById(Long id) throws Exception {
        invoiceValidation.findByIdValidation(id);
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<InvoiceModel> findAll() {
        return invoiceRepository.findAll();
    }
}
