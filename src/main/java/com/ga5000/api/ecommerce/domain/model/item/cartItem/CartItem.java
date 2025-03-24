package com.ga5000.api.ecommerce.domain.model.item.cartItem;

import com.ga5000.api.ecommerce.domain.model.cart.Cart;
import com.ga5000.api.ecommerce.domain.model.item.Item;
import com.ga5000.api.ecommerce.domain.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items_tb")
public class CartItem extends Item {

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public CartItem() {
       super();
    }

    public CartItem(String name, Product product, int quantity, BigDecimal price, Cart cart) {
        super(name, product, quantity, price);
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
