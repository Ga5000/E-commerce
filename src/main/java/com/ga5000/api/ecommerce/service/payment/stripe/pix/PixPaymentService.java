package com.ga5000.api.ecommerce.service.payment.stripe.pix;

import com.ga5000.api.ecommerce.domain.payment.utils.PaymentType;
import com.ga5000.api.ecommerce.repository.payment.PaymentRepository;
import com.ga5000.api.ecommerce.service.payment.AbstractPaymentService;
import org.springframework.beans.factory.annotation.Value;

public class PixPaymentService extends AbstractPaymentService {
    @Value("${stripe.secretKey}")
    private String secretKey;

    public PixPaymentService(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public void processPayment(double amount, PaymentType paymentType) {

    }
}
