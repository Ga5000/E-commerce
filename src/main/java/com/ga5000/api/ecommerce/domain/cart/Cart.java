package com.ga5000.api.ecommerce.domain.cart;

import com.ga5000.api.ecommerce.domain.baseEntity.purchase.AbstractPurchase;
import com.ga5000.api.ecommerce.domain.baseEntity.purchase.PurchaseItem;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.infrastructure.exceptions.ProductAlreadyOnCartException;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class Cart extends AbstractPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    public Cart() {}

    public Cart(Set<PurchaseItem> items, User user, double totalPrice, int totalItems, UUID cartId) {
        super(items, user, totalPrice, totalItems);
        this.cartId = cartId;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    @Override
    public void addItem(Product product, int quantity) {
        for (PurchaseItem item : getItems()) {
            if (item.getProduct().equals(product)) {
                throw new ProductAlreadyOnCartException("Este produto já está no carrinho");
            }
        }

        PurchaseItem newItem = new PurchaseItem(product, quantity);
        getItems().add(newItem);
        setTotalItems(getTotalItems() + 1);
    }

    public void removeItem(UUID purchaseItemId) {
        getItems().removeIf(item -> item.getPurchaseItemId().equals(purchaseItemId));
    }

}
