package com.ga5000.api.ecommerce.domain.baseEntity.purchase;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PurchaseItem {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID purchaseItemId;

    @ManyToOne
    private Product product;

    private int quantity;

    public PurchaseItem() {}

    public PurchaseItem(Product product, int quantity) {
        this.product = product;
    }

    public UUID getPurchaseItemId() {
        return purchaseItemId;
    }

    public void setPurchaseItemId(UUID purchaseItemId) {
        this.purchaseItemId = purchaseItemId;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice(){
        return product.getProductPrice() * quantity;
    }
}
