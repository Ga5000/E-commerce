package com.ga5000.api.ecommerce.utils.exceptions;

public class Message {

    public enum ProductMessage {
        PRODUCT_NOT_FOUND("Produto não encontrado"),
        PRODUCT_EXISTS("Este produto já existe")
        ;

        ProductMessage(String s) {
        }
    }

    public enum CommentMessage {
        COMMENT_NOT_FOUND("Comentário não encontrado");

        CommentMessage(String s) {
        }
    }

    public enum UserMessage {
      USER_NOT_FOUND("Usuário não encontrado");

        UserMessage(String s) {
        }
    }

    public enum AuthMessage {
        NO_AUTHENTICATED_USER_FOUND("Usuário não autenticado"),
        UNAUTHORIZED("Não autorizado");
        ;

        AuthMessage(String s) {
        }
    }

    public enum CategoryMessage {
        CATEGORY_NOT_FOUND("Categoria não encontrada"),
        CATEGORY_EXISTS("Esta categoria já existe");

        CategoryMessage(String s) {
        }
    }

    public enum BucketMessage {
        INVALID_FILE_TYPE("Tipo de arquivo inválido"),
        FILE_WITH_SAME_NAME_ALREADY_EXISTS("Já existe um arquivo com este nome");
        BucketMessage(String s) {
        }
    }

    public enum CartMessage{
        CART_NOT_FOUND("Carrinho não encontrado");
        CartMessage(String s){
        }
    }

    public enum AddressMessage{
        ADDRESS_NOT_FOUND("Endereço não encontrado"),
        ADDRESS_EXISTS("Este endereço já existe");
        AddressMessage(String s){
        }
    }
}
