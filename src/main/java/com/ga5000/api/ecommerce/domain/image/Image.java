package com.ga5000.api.ecommerce.domain.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "images")
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;

    private String objectId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    public Image(UUID imageId, String objectId, Product product) {
        this.imageId = imageId;
        this.objectId = objectId;
        this.product = product;
    }

    public Image(String objectId) {
        this.objectId = objectId;
    }

    public Image(String objectId, Product product) {
        this.objectId = objectId;
        this.product = product;
    }

    public Image() {
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
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
