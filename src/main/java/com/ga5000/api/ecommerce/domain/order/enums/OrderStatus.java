package com.ga5000.api.ecommerce.domain.order.enums;

public enum OrderStatus {
    SHIPPED("ENVIADO"),
    SHIPPING_IN_PROGRESS("ENVIO_EM_PROGRESSO"),
    SHIPPING_COMPLETED("ENVIO_COMPLETO"),
    CANCELLED("CANCELADO");

    OrderStatus(String status) {
    }
}
