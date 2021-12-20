package com.fakecompany.personservice.exception;

public class ImageNotComeBodyException extends GeneralRuntimeException{

    private static final long serialVersionUID = 1L;

    public ImageNotComeBodyException(String message){
        super(ImageNotComeBodyException.class, message);
    }

}
