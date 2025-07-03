package com.example.provapratica1.exception;

// Exceção de regra de negócio.
public class RegraNegocioException extends RuntimeException {
    // Construtor da exceção com uma mensagem.
    public RegraNegocioException(String message) {
        super(message);
    }
}