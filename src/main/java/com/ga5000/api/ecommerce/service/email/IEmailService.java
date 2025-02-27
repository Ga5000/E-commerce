package com.ga5000.api.ecommerce.service.email;

public interface IEmailService {
    void sendRecoverPasswordEmail(String email, String token);
}
