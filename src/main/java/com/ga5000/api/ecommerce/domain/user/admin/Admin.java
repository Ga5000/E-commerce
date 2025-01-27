package com.ga5000.api.ecommerce.domain.user.admin;

import com.ga5000.api.ecommerce.domain.review.Review;
import com.ga5000.api.ecommerce.domain.user.Role;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Admin extends User {

    public Admin(String name, String lastName, String email, String password, List<Review> reviews) {
        super(name, lastName, email, password, Role.ADMIN, reviews);
    }

    public Admin() {
        super();
    }
}
