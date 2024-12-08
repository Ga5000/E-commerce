package com.ga5000.api.ecommerce.adapters.outbounds.repository;

import com.ga5000.api.ecommerce.domain.baseEntity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
