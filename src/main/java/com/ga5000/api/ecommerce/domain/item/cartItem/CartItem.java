package com.ga5000.api.ecommerce.domain.item.cartItem;

import com.ga5000.api.ecommerce.domain.item.Item;
import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem extends Item {
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public CartItem(Product product, int quantity, Cart cart) {
        super(product, quantity);
        this.cart = cart;
    }

    public CartItem() {
        super();
    }

    public CartItem(Product product, int quantity) {
        super(product, quantity);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


}
