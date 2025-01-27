package com.ga5000.api.ecommerce.domain.item.cartItem;

import com.ga5000.api.ecommerce.domain.transaction.cart.Cart;
import com.ga5000.api.ecommerce.domain.item.Item;
import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem extends Item {

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;  // The cart that the item is in

    public CartItem(Product product, int quantity, double unitPrice, Cart cart) {
        super(product, quantity, unitPrice);
        this.cart = cart;
    }

    public CartItem() {
        super();
    }
}
