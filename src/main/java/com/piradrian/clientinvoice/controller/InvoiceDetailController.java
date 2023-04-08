package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-details")
public class InvoiceDetailController {

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @PostMapping
    public InvoiceDetailModel create(@RequestBody InvoiceDetailModel invoiceDetail) throws Exception {
        return invoiceDetailService.create(invoiceDetail);
    }

    @PutMapping("/")
    public InvoiceDetailModel update(@RequestBody InvoiceDetailModel invoiceDetail) throws Exception {
        return invoiceDetailService.update(invoiceDetail);
    }

    @GetMapping("/{id}")
    public InvoiceDetailModel findById(@PathVariable Long id) throws Exception {
        return invoiceDetailService.findById(id);
    }

    @GetMapping("/")
    public List<InvoiceDetailModel> findAll() {
        return invoiceDetailService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        invoiceDetailService.delete(id);
    }
}
