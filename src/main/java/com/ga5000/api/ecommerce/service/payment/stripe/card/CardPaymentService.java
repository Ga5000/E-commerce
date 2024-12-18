package com.ga5000.api.ecommerce.service.payment.stripe.card;

import com.ga5000.api.ecommerce.domain.payment.utils.PaymentType;
import com.ga5000.api.ecommerce.repository.payment.PaymentRepository;
import com.ga5000.api.ecommerce.service.payment.AbstractPaymentService;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class CardPaymentService extends AbstractPaymentService {
    @Value("${stripe.secretKey}")
    private String secretKey;

    public CardPaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public void processPayment(double amount, PaymentType paymentType) {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount * 100);
        chargeParams.put("currency", "BRL");
    }
}
