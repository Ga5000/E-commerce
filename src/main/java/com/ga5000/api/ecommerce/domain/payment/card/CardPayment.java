package com.ga5000.api.ecommerce.domain.payment.card;

import com.ga5000.api.ecommerce.domain.baseEntity.payment.Payment;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.PaymentType;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARD")
public class CardPayment extends Payment {
    public CardPayment() {super();}

    private String nameOnCard;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvc;
    private CardType cardType;

    public CardPayment(double amount, String nameOnCard, String cardNumber, String expiryMonth,
                       String expiryYear, String cvc) {
        super(PaymentType.CARD, amount, Status.PENDING);
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvc = cvc;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
