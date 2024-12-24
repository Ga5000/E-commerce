package com.ga5000.api.ecommerce.service.comment;

import com.ga5000.api.ecommerce.dto.CommentRequestDto;
import com.ga5000.api.ecommerce.dto.CommentResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ICommentService {
    void addComment(UUID productId, CommentRequestDto commentRequestDto) throws EntityNotFoundException;
    void respondComment(UUID commentId, CommentRequestDto commentRequestDto) throws EntityNotFoundException;
    void updateComment(UUID commentId, CommentRequestDto commentRequestDto) throws EntityNotFoundException;
    void deleteComment(UUID commentId) throws EntityNotFoundException;
    List<CommentResponseDto> getComments(UUID productId) throws EntityNotFoundException;
}
