package com.ga5000.api.ecommerce.domain.review;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID reviewId;

    @Column(name = "stars", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Size(min = 1, max = 5)
    private int stars; // max value is 5, min value is 1

    @Column(name = "created_at", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDate createdAt;

    @Column(name = "comment", nullable = false, length = 500)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Size(max = 500)
    private String comment;

    @Column(name = "edited", nullable = false)
    @JdbcTypeCode(SqlTypes.BOOLEAN)
    private boolean edited;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "parent_review_id")
    private Review parentReview;

    @OneToMany(mappedBy = "parentReview", cascade = CascadeType.ALL)
    private List<Review> responses; // List of child reviews (responses)

    public Review(UUID reviewId, int stars, String comment,
                  ReviewStatus reviewStatus, User user, Product product, Review parentReview, List<Review> responses) {
        this.reviewId = reviewId;
        this.stars = stars;
        this.createdAt = LocalDate.now();
        this.comment = comment;
        this.edited = false;
        this.reviewStatus = reviewStatus;
        this.user = user;
        this.product = product;
        this.parentReview = parentReview;
        this.responses = responses;
    }

    public Review() {
        this.createdAt = LocalDate.now();
        this.edited = false;
    }

    public Review(int stars, String comment, ReviewStatus reviewStatus,
                  User user, Product product, Review parentReview, List<Review> responses) {
        this.stars = stars;
        this.createdAt = LocalDate.now();
        this.comment = comment;
        this.edited = false;
        this.reviewStatus = reviewStatus;
        this.user = user;
        this.product = product;
        this.parentReview = parentReview;
        this.responses = responses;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Review getParentReview() {
        return parentReview;
    }

    public void setParentReview(Review parentReview) {
        this.parentReview = parentReview;
    }

    public List<Review> getResponses() {
        return responses;
    }

    public void setResponses(List<Review> responses) {
        this.responses = responses;
    }
}
