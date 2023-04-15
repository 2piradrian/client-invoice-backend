package com.piradrian.clientinvoice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "invoices")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;

    private LocalDateTime created_at;

    private Double total;

    @PrePersist
    private void prePersist() {
        created_at = LocalDateTime.now();
    }
}

