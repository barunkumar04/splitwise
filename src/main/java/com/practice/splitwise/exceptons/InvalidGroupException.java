package com.practice.splitwise.exceptons;

public class InvalidGroupException extends Exception{

    public InvalidGroupException(String errorMessage){
        super(errorMessage);
    }
}
