package com.ga5000.api.ecommerce.domain.payment.enums;

public enum PaymentStatus {
    PENDING("AGUARDANDO"),
    APPROVED("APROVADO"),
    REJECTED("REJEITADO");

    PaymentStatus(String status) {
    }
}
