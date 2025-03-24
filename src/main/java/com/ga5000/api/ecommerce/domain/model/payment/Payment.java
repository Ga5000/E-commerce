package com.ga5000.api.ecommerce.domain.model.payment;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "payments_tb")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
