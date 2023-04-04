package com.piradrian.clientinvoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_detail_id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceModel invoiceModel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    private int amount;

    private double price;

}