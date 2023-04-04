package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice-details")
public class InvoiceDetailController {

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @PostMapping
    public InvoiceDetailModel create(@RequestBody InvoiceDetailModel invoiceDetail) {
        return invoiceDetailService.create(invoiceDetail);
    }

    @PutMapping("/{id}")
    public InvoiceDetailModel update(@PathVariable Long id, @RequestBody InvoiceDetailModel invoiceDetail) {
        invoiceDetail.setInvoice_detail_id(id);
        return invoiceDetailService.update(invoiceDetail);
    }

    @GetMapping("/{id}")
    public InvoiceDetailModel findById(@PathVariable Long id) {
        return invoiceDetailService.findById(id);
    }

    @GetMapping
    public List<InvoiceDetailModel> findAll() {
        return invoiceDetailService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        invoiceDetailService.delete(id);
    }
}
