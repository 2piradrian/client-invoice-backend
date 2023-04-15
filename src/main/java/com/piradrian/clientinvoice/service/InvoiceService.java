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
        return createInvoices(invoiceDetail, client);
    }

    public List<InvoiceModel> create(List<InvoiceDetailModel> invoiceDetailList, ClientModel client) throws Exception {
        List<InvoiceModel> addedInvoices = new ArrayList<>();
       for(InvoiceDetailModel invoiceDetail : invoiceDetailList) {
              addedInvoices.add(createInvoices(invoiceDetail, client));
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

    private InvoiceModel setInvoice(ClientModel client, InvoiceDetailModel invoiceDetail){
        // Crea el objeto factura
        InvoiceModel invoice = new InvoiceModel();
        // Obtiene el producto
        Optional<ProductModel> paramProduct = productRepository.findById(invoiceDetail.getProductModel().getId());
        // Setea los valores de la factura
        invoice.setClientModel(client);
        invoice.setTotal(paramProduct.get().getPrice() * invoiceDetail.getAmount());

        return invoice;
    }

    private InvoiceModel createInvoices(InvoiceDetailModel invoiceDetail, ClientModel client) throws Exception {
        // Valida el detalle de la factura
        invoiceValidation.createValidation(invoiceDetail, client);
        // Crea la factura
        InvoiceModel invoiceCreated = invoiceRepository.save(setInvoice(client, invoiceDetail));
        // Crea el detalle de la factura
        invoiceDetailService.create(invoiceDetail, invoiceCreated);
        return invoiceCreated;
    }
}
