package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public InvoiceModel create(@RequestBody InvoiceModel invoice) {
        return invoiceService.create(invoice);
    }

    @PutMapping("/{id}")
    public InvoiceModel update(@PathVariable Long id, @RequestBody InvoiceModel invoice) {
        invoice.setId(id);
        return invoiceService.update(invoice);
    }

    @GetMapping("/{id}")
    public InvoiceModel findById(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    @GetMapping("/")
    public List<InvoiceModel> findAll() {
        return invoiceService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        invoiceService.delete(id);
    }
}
