package com.github.adamflorczak.passwordholder.businesslogic;

public class WrongUserAndServiceException extends RuntimeException {

    public WrongUserAndServiceException(){

    }

    public WrongUserAndServiceException(String message){
        System.out.println(message);
    }
}
