package com.ga5000.api.ecommerce.dto.comment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.comment.Comment}
 */
public record CommentRequestDto(@NotBlank(message = "O comentário não pode estar vazio") String comment,
                                @Max(5) int rating) implements Serializable {
}