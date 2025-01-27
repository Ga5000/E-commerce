package com.ga5000.api.ecommerce.domain.user;

import com.ga5000.api.ecommerce.domain.review.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID userId;

    @Column(name = "name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "last_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotBlank(message = "E-mail is mandatory")
    @Email(message = "E-mail is not valid")
    private String email;

    @Column(name = "password", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "created_at", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public User(UUID userId, String name, String lastName, String fullName, String email, String password, Role role,
                List<Review> reviews) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.role = role;
        this.reviews = reviews;
    }

    public User() {
        this.createdAt = LocalDate.now();
    }

    public User(String name, String lastName, String email, String password, Role role, List<Review> reviews) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.role = role;
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @PrePersist
    @PreUpdate
    private void updateFullName() {
        this.fullName = name + " " + lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name());
    }

    @Override
    public String getUsername() {
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin(){
        return this.role == Role.ADMIN;
    }
}
