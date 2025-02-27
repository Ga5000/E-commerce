package com.ga5000.api.ecommerce.service.cart;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.cart.CartResponse;
import com.ga5000.api.ecommerce.dto.item.AddItemRequest;
import com.ga5000.api.ecommerce.exception.item.ItemAlreadyOnCartException;
import com.ga5000.api.ecommerce.exception.item.ItemNotFoundException;
import com.ga5000.api.ecommerce.exception.product.ProductNotFoundException;
import com.ga5000.api.ecommerce.repository.cart.CartRepository;
import com.ga5000.api.ecommerce.repository.item.CartItemRepository;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.RepositoryUtils.findByIdOrThrow;

@Service
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;
    private final DtoMapper dtoMapper;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       ProductRepository productRepository, AuthService authService, DtoMapper dtoMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.authService = authService;
        this.dtoMapper = dtoMapper;
    }

    @Transactional
    @Override
    public void addItemToCart(AddItemRequest addItemRequest) throws ItemAlreadyOnCartException {
        Product product = findByIdOrThrow(addItemRequest.productId(), productRepository, ProductNotFoundException::new);
        Cart cart = cartRepository.findCartByUser_Email(authService.getAuthenticatedUserEmail());
        CartItem item = new CartItem(product, addItemRequest.quantity());
        if(cartItemRepository.isItemAlreadyOnCart(product.getProductId(), cart.getCartId())){
            throw new ItemAlreadyOnCartException();
        }
        item.setCart(cart);
        saveItem(item);
        cart.addItem(item);
        saveCart(cart);
    }

    @Transactional
    @Override
    public void removeItemFromCart(UUID itemId) {
        CartItem item = findByIdOrThrow(itemId, cartItemRepository, ItemNotFoundException::new);
        Cart cart = cartRepository.findCartByUser_Email(authService.getAuthenticatedUserEmail());
        cart.removeItem(item);
        saveCart(cart);
    }

    @Transactional
    @Override
    public void updateQuantity(UUID itemId) {
        CartItem item = findByIdOrThrow(itemId, cartItemRepository, ItemNotFoundException::new);
        item.setQuantity(item.getQuantity() + 1);
        saveItem(item);
    }

    @Override
    public void checkout() {}

    @Override
    public CartResponse getCart() {
        Cart cart = cartRepository.findCartByUser_Email(authService.getAuthenticatedUserEmail());

        return dtoMapper.toCartResponse(cart);
    }

    private void saveCart(Cart cart){
        cartRepository.save(cart);
    }

    private void saveItem(CartItem item){
        cartItemRepository.save(item);
    }
}
