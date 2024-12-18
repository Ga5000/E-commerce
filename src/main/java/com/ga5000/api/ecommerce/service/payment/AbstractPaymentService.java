package com.ga5000.api.ecommerce.service.payment;

import ch.qos.logback.core.status.Status;
import com.ga5000.api.ecommerce.domain.payment.Payment;
import com.ga5000.api.ecommerce.domain.payment.utils.PaymentType;
import com.ga5000.api.ecommerce.repository.payment.PaymentRepository;

public abstract class AbstractPaymentService {
    protected final PaymentRepository paymentRepository;


    public AbstractPaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public abstract void processPayment(double amount, PaymentType paymentType);


    protected void logPayment(double amount, PaymentType paymentType, Status status) {
        Payment payment = new Payment(paymentType, amount, status);
        paymentRepository.save(payment);
    }
}
