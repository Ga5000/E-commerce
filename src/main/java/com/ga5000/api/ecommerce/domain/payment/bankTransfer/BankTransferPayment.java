package com.ga5000.api.ecommerce.domain.payment.bankTransfer;

import com.ga5000.api.ecommerce.domain.baseEntity.payment.Payment;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.PaymentType;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BANK_TRANSFER")
public class BankTransferPayment extends Payment {
    private String bankTransactionId;
    private String bankName;
    private String accountHolder;
    private String bankBranch;
    private String bankCode;


    public BankTransferPayment() {
        super();
    }

    public BankTransferPayment(double amount, String status, String bankTransactionId, String bankName,
                               String accountHolder, String bankBranch, String bankCode) {
        super(PaymentType.BANK_TRANSFER, amount, Status.PENDING);
        this.bankTransactionId = bankTransactionId;
        this.bankName = bankName;
        this.accountHolder = accountHolder;
        this.bankBranch = bankBranch;
        this.bankCode = bankCode;
    }


    public String getBankTransactionId() {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
