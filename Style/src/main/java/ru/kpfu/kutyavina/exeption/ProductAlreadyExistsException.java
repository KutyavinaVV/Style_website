package ru.kpfu.kutyavina.exeption;


public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException (String message) {
        super(message);
    }
}
