package com.ga5000.api.ecommerce.controller.comment;

import com.ga5000.api.ecommerce.dto.comment.CommentRequestDto;
import com.ga5000.api.ecommerce.dto.comment.CommentResponseDto;
import com.ga5000.api.ecommerce.service.comment.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")

public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addComment(@RequestParam UUID productId, @RequestBody @Valid CommentRequestDto commentRequestDto){
        commentService.addComment(productId, commentRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Comentário adicionado com sucesso!");
    }

    @PostMapping("/respond")
    public ResponseEntity<String> respondComment(@RequestParam UUID commentId, @RequestBody @Valid CommentRequestDto commentRequestDto){
        commentService.respondComment(commentId, commentRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Resposta adicionada com sucesso!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateComment(@RequestParam UUID commentId, @RequestBody @Valid CommentRequestDto commentRequestDto){
        commentService.updateComment(commentId, commentRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("Comentário atualizado com sucesso!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam UUID commentId){
        commentService.deleteComment(commentId);

        return ResponseEntity.status(HttpStatus.OK).body("Comentário deletado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComment(@RequestParam UUID productId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments(productId));
    }




}
