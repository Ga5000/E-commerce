package com.ga5000.api.ecommerce.repository.payment;

import com.ga5000.api.ecommerce.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
