package com.challenges.MarketPlace.useCase.exceptions;

public class EntityNotExistException extends RuntimeException{
    public EntityNotExistException (String message){super(message);}
}
