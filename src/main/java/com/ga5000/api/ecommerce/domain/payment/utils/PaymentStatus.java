package com.ga5000.api.ecommerce.domain.payment.utils;

public enum PaymentStatus {
    PENDING("AGUARDANDO"),
    APPROVED("APROVADO"),
    REJECTED("REJEITADO");

    PaymentStatus(String status) {
    }
}
