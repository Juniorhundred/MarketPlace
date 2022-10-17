package com.challenges.MarketPlace.useCase.exceptions;

public class ConteudoNaoEncontradoException extends RuntimeException {
    public ConteudoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}