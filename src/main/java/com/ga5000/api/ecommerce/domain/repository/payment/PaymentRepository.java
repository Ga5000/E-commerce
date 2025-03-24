package com.ga5000.api.ecommerce.domain.repository.payment;

import com.ga5000.api.ecommerce.domain.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
