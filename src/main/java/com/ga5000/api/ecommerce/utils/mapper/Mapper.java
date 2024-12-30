package com.ga5000.api.ecommerce.utils.mapper;

import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.dto.address.AddressResponseDto;
import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.comment.Comment;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.cart.CartResponseDto;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.dto.comment.CommentResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;

import java.util.stream.Collectors;

public class Mapper {

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getComment(),
                comment.getRating(),
                comment.getCreationDate(),
                comment.getModifiedDate()
        );
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }

    public static ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getInventory(),
                product.getImages(),
                product.getCategories().stream().map(Mapper::toCategoryResponseDto).collect(Collectors.toSet())
        );
    }

    public static CartResponseDto toCartResponseDto(Cart cart){
        return new CartResponseDto(
                cart.getCartId(),
                cart.getItems(),
                cart.getTotalPrice()
        );
    }

    public static AddressResponseDto toAddressResponseDto(Address address){
        return new AddressResponseDto(
                address.getAddressId(),
                address.getCep(),
                address.getStreet(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getFormattedAddress()
        );
    }
}
