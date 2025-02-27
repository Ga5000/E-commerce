package com.ga5000.api.ecommerce.service.image;

import com.ga5000.api.ecommerce.domain.image.Image;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.image.ImageRequest;
import com.ga5000.api.ecommerce.dto.image.ImageResponse;

import java.util.List;
import java.util.UUID;

public interface IImageService {
    void addImageToProduct(ImageRequest imageRequest);
    void removeImage(Image image);
    List<ImageResponse> getProductImages(Product product);
}
