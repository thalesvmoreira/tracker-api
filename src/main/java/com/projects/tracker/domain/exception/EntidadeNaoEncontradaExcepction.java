package com.projects.tracker.domain.exception;

public class EntidadeNaoEncontradaExcepction extends NegocioException{

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaExcepction(String message){
        super(message);
    }
}
