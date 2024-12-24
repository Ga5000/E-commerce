package com.ga5000.api.ecommerce.domain.comment;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "O comentário não pode estar vazio")
    private String comment;

    @Column(nullable = false)
    @Max(value = 5, message = "A avaliação deve ser no máximo 5")
    private int rating;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();

    public Comment() {
        this.creationDate = LocalDateTime.now();
    }

    public Comment(String comment, int rating, User user, Product product, Comment parentComment) {
        this();
        this.comment = comment;
        this.rating = rating;
        this.user = user;
        this.product = product;
        this.parentComment = parentComment;
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
        this.modifiedDate = this.creationDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    public UUID getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
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

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void addChildComment(Comment childComment) {
        this.childComments.add(childComment);
        childComment.setParentComment(this);
    }

    public void removeChildComment(Comment childComment) {
        this.childComments.remove(childComment);
        childComment.setParentComment(null);
    }
}

