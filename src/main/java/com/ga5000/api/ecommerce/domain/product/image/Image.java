package com.ga5000.api.ecommerce.domain.product.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID imageId;

    @Column(name = "url", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotNull(message = "URL is mandatory")
    @NotBlank(message = "URL is mandatory")
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "productId")
    private Product product;

    public Image(UUID imageId, String url, Product product) {
        this.imageId = imageId;
        this.url = url;
        this.product = product;
    }

    public Image() {
    }

    public Image(String url, Product product) {
        this.url = url;
        this.product = product;
    }

    public Image(Product product) {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }
}
