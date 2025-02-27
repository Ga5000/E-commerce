package com.ga5000.api.ecommerce.utils;

import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.image.Image;
import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.item.orderItem.OrderItem;
import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.address.AddressResponse;
import com.ga5000.api.ecommerce.dto.cart.CartResponse;
import com.ga5000.api.ecommerce.dto.item.cart.CartItemResponse;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;
import com.ga5000.api.ecommerce.dto.image.ImageResponse;
import com.ga5000.api.ecommerce.dto.item.order.OrderItemResponse;
import com.ga5000.api.ecommerce.dto.product.ProductResponseInfo;
import com.ga5000.api.ecommerce.service.minioS3.MinioS3Service;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DtoMapper {

    private final MinioS3Service minioS3Service;

    public DtoMapper(MinioS3Service minioS3Service) {
        this.minioS3Service = minioS3Service;
    }

    public CategoryResponse toCategoryResponse(Category category){
        return new CategoryResponse(
                category.getCategoryId(),
                category.getName()
        );
    }

    public ImageResponse toImageResponse(Image image){
        return new ImageResponse(
                image.getImageId(),
                minioS3Service.getUrl(image.getObjectId())
        );
    }

    public ProductResponseInfo toProductResponseInfo(Product product,
                                                     List<CategoryResponse> categoryResponses,
                                                     List<ImageResponse> imageResponses){

        return new ProductResponseInfo(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getDiscount(),
                categoryResponses,
                imageResponses
        );
    }

    public CartItemResponse toCartItemResponse(CartItem item){
        return new CartItemResponse(
                item.getItemId(),
                item.getProduct().getName(),
                toImageResponse(item.getProduct().getImages().get(0)),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getTotalItemPrice()
        );
    }

    public CartResponse toCartResponse(Cart cart){
        return new CartResponse(
                cart.getCartId(),
                cart.getCartItems().stream().map(this::toCartItemResponse).toList(),
                cart.getTotalAmount()
        );
    }

    public AddressResponse toAddressResponse(Address address){
        return new AddressResponse(
                address.getAddressId(),
                address.getCompleteAddress(),
                address.getZipCode()
        );
    }

    public OrderItemResponse toOrderItemResponse(OrderItem item){
        return new OrderItemResponse(
                item.getItemId(),
                item.getProduct().getName(),
                toImageResponse(item.getProduct().getImages().get(0)),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getTotalItemPrice()
        );
    }

    public OrderResponse toOrderResponse(Order order){
        return new OrderResponse(
                order.getOrderId(),
                toAddressResponse(order.getDeliveryAddress()),
                order.getOrderItems().size(),
                order.getOrderItems().stream().map(this::toOrderItemResponse).toList(),
                order.getTotalAmount(),
                order.getSelectedInstallments(),
                order.getOrderDate()
        );
    }
}
