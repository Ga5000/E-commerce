package com.ga5000.api.ecommerce.dto.comment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.comment.Comment}
 */
public record CommentResponseDto(UUID commentId,
                                 @NotBlank(message = "O comentário não pode estar vazio") String comment,
                                 @Max(5) int rating, LocalDateTime creationDate, LocalDateTime modifiedDate) implements Serializable {
}