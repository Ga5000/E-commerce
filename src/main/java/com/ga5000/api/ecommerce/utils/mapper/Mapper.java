package com.ga5000.api.ecommerce.utils.mapper;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.comment.Comment;
import com.ga5000.api.ecommerce.dto.CategoryResponseDto;
import com.ga5000.api.ecommerce.dto.CommentResponseDto;

public class Mapper {

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getComment(),
                comment.getRating(),
                comment.getCreationDate(),
                comment.getModifiedDate()
        );
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }
}
