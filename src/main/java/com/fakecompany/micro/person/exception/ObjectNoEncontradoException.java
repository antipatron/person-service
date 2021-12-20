package com.fakecompany.micro.person.exception;

public class ObjectNoEncontradoException extends GeneralRuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNoEncontradoException(String message){
        super(ObjectNoEncontradoException.class,message);
    }

}
