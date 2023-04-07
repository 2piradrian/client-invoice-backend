package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.InvoiceModel;
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
    public ResponseEntity<InvoiceModel> create(@RequestBody InvoiceModel invoice) throws Exception {
        return new ResponseEntity<>(invoiceService.create(invoice), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<InvoiceModel> update(@RequestBody InvoiceModel invoice) throws Exception{
        return new ResponseEntity<>(invoiceService.update(invoice), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceModel> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(invoiceService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<InvoiceModel>> findAll() {
        return new ResponseEntity<>(invoiceService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceModel> delete(@PathVariable Long id) throws Exception {
        invoiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
