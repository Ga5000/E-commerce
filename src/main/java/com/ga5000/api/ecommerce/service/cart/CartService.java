package com.ga5000.api.ecommerce.service.cart;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.product.productItem.ProductItem;
import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.dto.cart.AddItemDto;
import com.ga5000.api.ecommerce.dto.cart.CartResponseDto;
import com.ga5000.api.ecommerce.repository.cart.CartRepository;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.utils.Mapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private static final String CART_NOT_FOUND_MESSAGE = "Carrinho não encontrado";
    private static final String USER_NOT_FOUND_MESSAGE = "Usuário não encontrado";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Produto não encontrado";

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addItemToCart(AddItemDto addItemDto) throws EntityNotFoundException {
        ProductItem newItem = createProductItem(addItemDto.productId());
        Cart cart = getCartById(addItemDto.cartId());

        cart.getItems().stream()
                .filter(item -> item.getName().equalsIgnoreCase(newItem.getName()))
                .findFirst()
                .ifPresentOrElse(
                        existingItem -> existingItem.setQuantity(existingItem.getQuantity() + 1),
                        () -> cart.addItem(newItem)
                );

        cartRepository.save(cart);
    }


    @Override
    public void removeItemFromCart(UUID cartId, int index) throws EntityNotFoundException {
        Cart cart = getCartById(cartId);
        cart.removeItem(index);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(UUID cartId) throws EntityNotFoundException {
        Cart cart = getCartById(cartId);
        cart.clear();
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(UUID cartId, int index) throws EntityNotFoundException {
        Cart cart = getCartById(cartId);
        cart.getItems().get(index).setQuantity(cart.getItems().get(index).getQuantity() + 1);
        cartRepository.save(cart);
    }

    @Override
    public CartResponseDto displayCart(UUID userId) throws EntityNotFoundException {
        Cart cart = getUserCart(userId);
        return Mapper.toCartResponseDto(cart);
    }

    private Cart getCartById(UUID cartId) throws EntityNotFoundException {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException(CART_NOT_FOUND_MESSAGE));
    }

    private Cart getUserCart(UUID userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE));
        return user.getCart();
    }

    private ProductItem createProductItem(UUID productId) throws EntityNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_MESSAGE));
        String productImage = product.getImages().isEmpty() ? null : product.getImages().get(0);

        return new ProductItem(product.getName(), product.getPrice(), productImage, 1);
    }
}
