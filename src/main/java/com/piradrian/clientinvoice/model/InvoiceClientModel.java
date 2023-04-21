package com.piradrian.clientinvoice.model;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceClientModel {

    public List<InvoiceDetailModel> invoiceDetailList;

    public ClientModel client;

}
