package com.ga5000.api.ecommerce.service.comment;

import com.ga5000.api.ecommerce.domain.comment.Comment;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.dto.comment.CommentRequestDto;
import com.ga5000.api.ecommerce.dto.comment.CommentResponseDto;
import com.ga5000.api.ecommerce.exception.UnauthorizedException;
import com.ga5000.api.ecommerce.repository.comment.CommentRepository;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import com.ga5000.api.ecommerce.utils.AuthUtil;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import com.ga5000.api.ecommerce.utils.mapper.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public CommentService(CommentRepository commentRepository, ProductRepository productRepository,
                          AuthService authService) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.authService = authService;
    }

    @Transactional
    @Override
    public void addComment(UUID productId, CommentRequestDto commentRequestDto) throws EntityNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Message.ProductMessage.PRODUCT_NOT_FOUND.name()));

        User currentUser = authService.getCurrentUser();
        var comment = new Comment();
        BeanUtils.copyProperties(commentRequestDto, comment);
        comment.setProduct(product);
        comment.setUser(currentUser);

        product.getComments().add(comment);
        currentUser.getComments().add(comment);

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void respondComment(UUID commentId, CommentRequestDto commentRequestDto) throws EntityNotFoundException {
        Comment parentComment = getById(commentId);

        var responseComment = new Comment();
        BeanUtils.copyProperties(commentRequestDto, responseComment);
        responseComment.setParentComment(parentComment);
        responseComment.setUser(authService.getCurrentUser());

        commentRepository.save(responseComment);

        parentComment.getChildComments().add(responseComment);
    }


    @Transactional
    @Override
    public void updateComment(UUID commentId, CommentRequestDto commentRequestDto) throws EntityNotFoundException {
        Comment comment = getById(commentId);
        User currentUser = authService.getCurrentUser();

        if(!currentUser.equals(comment.getUser())) {
            throw new UnauthorizedException(Message.AuthMessage.UNAUTHORIZED.name());
        }
        BeanUtils.copyProperties(commentRequestDto, comment);
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(UUID commentId) throws EntityNotFoundException {
        Comment comment = getById(commentId);
        User currentUser = authService.getCurrentUser();
        if(!currentUser.equals(comment.getUser()) || !AuthUtil.IsAdmin(currentUser)) {
            throw new UnauthorizedException(Message.AuthMessage.UNAUTHORIZED.name());
        }
        comment.getUser().getComments().remove(comment);
        comment.getProduct().getComments().remove(comment);

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponseDto> getComments(UUID productId) throws EntityNotFoundException {
        return commentRepository.
                findCommentsByProduct_ProductId(productId, Sort.by("creationDate").descending())
                .stream().map(Mapper::toCommentResponseDto).toList();

    }

    private Comment getById(UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(Message.CommentMessage.COMMENT_NOT_FOUND.name()));
    }
}
