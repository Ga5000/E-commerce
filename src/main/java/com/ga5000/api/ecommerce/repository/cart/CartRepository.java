package com.ga5000.api.ecommerce.repository.cart;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("SELECT c FROM Cart c JOIN  c.user u WHERE u.email = :email")
    Cart findCartByUser_Email(String email);


}
