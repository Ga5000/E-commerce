package com.ga5000.api.ecommerce.domain.item;

import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID itemId;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    private int quantity;

    public Item(UUID itemId, Product product, int quantity) {
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item(){
    }

    public double getUnitPrice(){
        return getProduct().getPrice();
    }

    public double getTotalItemPrice(){
        return getUnitPrice() * getQuantity();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.getQuantity() && Objects.equals(product.getProductId(), cartItem.getProduct().getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getProductId(), quantity);
    }
}
