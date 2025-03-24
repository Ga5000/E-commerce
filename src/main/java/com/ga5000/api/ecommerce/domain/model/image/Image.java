package com.ga5000.api.ecommerce.domain.model.image;

import com.ga5000.api.ecommerce.domain.model.item.Item;
import com.ga5000.api.ecommerce.domain.model.product.Product;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "images_tb")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String objectId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    public Image() {}

    public Image(String objectId, Product product) {
        this.objectId = objectId;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
