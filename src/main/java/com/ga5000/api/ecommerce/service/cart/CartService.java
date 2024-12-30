package com.ga5000.api.ecommerce.service.cart;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.product.productItem.ProductItem;
import com.ga5000.api.ecommerce.dto.cart.CartResponseDto;
import com.ga5000.api.ecommerce.exception.UnauthorizedException;
import com.ga5000.api.ecommerce.repository.cart.CartRepository;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import com.ga5000.api.ecommerce.utils.mapper.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, AuthService authService, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.authService = authService;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(UUID cartId, UUID productId) throws EntityNotFoundException{
        Cart cart = getById(cartId);
        compareCartId(cartId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Message.ProductMessage.PRODUCT_NOT_FOUND.name()));


        Optional<ProductItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getName().equals(product.getName()))
                .findFirst();

        existingItem.ifPresent(productItem -> productItem.setQuantity(productItem.getQuantity() + 1));
        cart.addItem(new ProductItem(product.getName(), product.getPrice(), product.getImages().get(0), 1));


        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(UUID cartId, int index){
        Cart cart = getById(cartId);

        compareCartId(cartId);
        cart.removeItem(index);
    }

    @Override
    public void updateQuantity(UUID cartId, int index, int newQuantity){
        Cart cart = getById(cartId);
        compareCartId(cartId);

        cart.getItems().get(index).setQuantity(newQuantity);
        cartRepository.save(cart);
    }


    @Override
    public void clearCart(UUID cartId){
        Cart cart = getById(cartId);

        compareCartId(cartId);
        cart.clear();
    }

    @Override
    public CartResponseDto getCart() {
        return Mapper.toCartResponseDto(authService.getCurrentUser().getCart());
    }

    private void compareCartId(UUID cartId) throws UnauthorizedException {
        if(!authService.getCurrentUser().getCart().getCartId().equals(cartId)) {
            throw new UnauthorizedException(Message.AuthMessage.UNAUTHORIZED.name());
        }
    }

    private Cart getById(UUID cartId) throws EntityNotFoundException {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException(Message.CartMessage.CART_NOT_FOUND.name()));

    }
}
