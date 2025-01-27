package com.ga5000.api.ecommerce.domain.item;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@MappedSuperclass
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID itemId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;  // The product being added to the cart or order

    @Column(name = "quantity", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;  // The quantity of the product

    @Column(name = "unit_price", nullable = false)
    @JdbcTypeCode(SqlTypes.DOUBLE)
    private double unitPrice;  // The price of the product * quantity

    public Item(UUID itemId, Product product, int quantity, double unitPrice) {
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Item() {
    }

    public Item(Product product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return this.unitPrice * this.quantity;
    }
}
