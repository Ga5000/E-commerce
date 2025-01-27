package com.ga5000.api.ecommerce.domain.user.client;

import com.ga5000.api.ecommerce.domain.transaction.cart.Cart;
import com.ga5000.api.ecommerce.domain.transaction.order.Order;
import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.domain.address.Address;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Client extends User {

    private String cpf;

    @Column(name = "stripe_client_id")
    private String stripeClientId;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;





    public Client() {
    }
}
