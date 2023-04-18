package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.model.InvoiceDetailModel;
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
    public ResponseEntity<List<InvoiceModel>> create(@RequestBody InvoiceDetailRequest request) throws Exception {
        return new ResponseEntity<>(invoiceService.create(request.invoiceDetailList, request.client), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceModel> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(invoiceService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<InvoiceModel>> findAll() {
        return new ResponseEntity<>(invoiceService.findAll(), HttpStatus.OK);
    }

    private static class InvoiceDetailRequest {
        public List<InvoiceDetailModel> invoiceDetailList;
        public ClientModel client;

        public InvoiceDetailRequest(List<InvoiceDetailModel> invoiceDetail, ClientModel client) {
            this.invoiceDetailList = invoiceDetail;
            this.client = client;
        }
    }


}
