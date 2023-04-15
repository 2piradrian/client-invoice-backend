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

    public InvoiceModel create(InvoiceDetailModel invoiceDetail, ClientModel client) throws Exception {
        InvoiceModel invoice = createInvoices(invoiceDetail, client);
        InvoiceModel invoiceCreated = invoiceRepository.save(invoice);
        // Crea el detalle de la factura
        invoiceDetailService.create(invoiceDetail, invoiceCreated);
        return invoiceCreated;
    }

    public List<InvoiceModel> create(List<InvoiceDetailModel> invoiceDetailList, ClientModel client) throws Exception {
       List<InvoiceModel> addedInvoices = new ArrayList<>();
       double total = 0;
       for(InvoiceDetailModel invoiceDetail : invoiceDetailList) {
             Optional<ProductModel> paramProduct = productRepository.findById(invoiceDetail.getProductModel().getId());
             invoiceValidation.existsByIdValidation(paramProduct);
             invoiceDetail.setProductModel(paramProduct.get());

             total += invoiceDetail.getAmount() * invoiceDetail.getProductModel().getPrice();
       }
         InvoiceModel invoice = new InvoiceModel();
         invoice.setClientModel(client);
         invoice.setTotal(total);

         invoiceValidation.createValidation(client);
         InvoiceModel invoiceCreated = invoiceRepository.save(invoice);

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

    private InvoiceModel createInvoices(InvoiceDetailModel invoiceDetail, ClientModel client) throws Exception {
        Optional<ProductModel> paramProduct = productRepository.findById(invoiceDetail.getProductModel().getId());
        invoiceValidation.existsByIdValidation(paramProduct);
        invoiceDetail.setProductModel(paramProduct.get());

        // Crea el objeto factura
        InvoiceModel invoice = new InvoiceModel();
        // Obtiene el producto
        // Setea los valores de la factura
        invoice.setClientModel(client);
        invoice.setTotal(paramProduct.get().getPrice() * invoiceDetail.getAmount());

        // Valida el detalle de la factura
        invoiceValidation.createValidation(invoiceDetail, client);
        return invoice;
    }
}
