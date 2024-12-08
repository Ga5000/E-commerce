package com.ga5000.api.ecommerce.domain.payment.pix;

import com.ga5000.api.ecommerce.domain.baseEntity.payment.Payment;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.PaymentType;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PIX")
public class PixPayment extends Payment {

    private String pixTransactionId; // chave pix

    private String qrCode;

    public PixPayment() {super();}

    public PixPayment(double amount, String pixTransactionId, String qrCode) {
        super(PaymentType.PIX, amount, Status.PENDING);
        this.pixTransactionId = pixTransactionId;
        this.qrCode = qrCode;
    }

    public String getPixTransactionId() {
        return pixTransactionId;
    }

    public void setPixTransactionId(String pixTransactionId) {
        this.pixTransactionId = pixTransactionId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
