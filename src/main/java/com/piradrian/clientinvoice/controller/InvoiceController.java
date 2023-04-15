package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/")
    public ResponseEntity<InvoiceModel> create(@RequestBody InvoiceDetailModel invoiceDetail, ClientModel client) throws Exception {
        return new ResponseEntity<>(invoiceService.create(invoiceDetail, client), HttpStatus.OK);
    }

    @PostMapping("/list/")
    public ResponseEntity<List<InvoiceModel>> create(@RequestBody List<InvoiceDetailModel> invoiceDetailList, ClientModel client) throws Exception {
        return new ResponseEntity<>(invoiceService.create(invoiceDetailList, client), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceModel> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(invoiceService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<InvoiceModel>> findAll() {
        return new ResponseEntity<>(invoiceService.findAll(), HttpStatus.OK);
    }

}
