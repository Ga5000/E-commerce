package com.ga5000.api.ecommerce.domain.payment.utils;

public enum PaymentType {
    CARD("CARTÃO"),
    PIX("PIX"),
    BANK_TRANSFER("TRANSFERÊNCIA_BANCÁRIA");

    PaymentType(String type) {
    }
}
