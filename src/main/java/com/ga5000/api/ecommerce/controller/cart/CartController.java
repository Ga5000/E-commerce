package com.ga5000.api.ecommerce.controller.cart;

import com.ga5000.api.ecommerce.dto.cart.CartResponse;
import com.ga5000.api.ecommerce.dto.item.AddItemRequest;
import com.ga5000.api.ecommerce.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-item")
    public ResponseEntity<Map<String, String>> addItemToCart(
            @RequestParam UUID productId,
            @RequestParam int quantity) {
        cartService.addItemToCart(new AddItemRequest(productId, quantity));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("success", "Item successfully added to cart"));
    }

    @PatchMapping("/update/quantity")
    public ResponseEntity<Map<String, String>> updateQuantity(@RequestParam UUID itemId){
        cartService.updateQuantity(itemId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("success", "Item quantity increased"));
    }


    @DeleteMapping("/remove-item")
    public ResponseEntity<Map<String, String>> removeItemFromCart(@RequestParam UUID itemId) {
        cartService.removeItemFromCart(itemId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("success", "Item successfully removed from cart"));
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, String>> checkout() {
       return null;
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }
}
