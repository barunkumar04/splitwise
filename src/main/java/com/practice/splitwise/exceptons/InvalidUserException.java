package com.practice.splitwise.exceptons;

public class InvalidUserException extends Exception{

    public InvalidUserException(String errorMessage){
        super(errorMessage);
    }
}
