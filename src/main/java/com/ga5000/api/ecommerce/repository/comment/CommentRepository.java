package com.ga5000.api.ecommerce.repository.comment;

import com.ga5000.api.ecommerce.domain.comment.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findCommentsByProduct_ProductId(UUID productId, Sort creationDate);
}
