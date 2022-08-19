package com.challenges.MarketPlace.dataProvider.ExceptionsDataProvider;

public class ValidarCadastroException extends RuntimeException {

    public ValidarCadastroException (String message, Exception exception){super(message);}

}
