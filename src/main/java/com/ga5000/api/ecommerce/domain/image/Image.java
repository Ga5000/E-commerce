package com.ga5000.api.ecommerce.domain.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "images")
public class Image{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;

    @Column
    private byte[] image;

    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
    private Product product;

    public Image() {}

    public Image(UUID imageId, byte[] image, Product product) {
        this.imageId = imageId;
        this.image = image;
        this.product = product;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
