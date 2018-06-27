package oop.ex6.validator;

public class TypeOneException extends Exception {
    /**
     * this Exception is thrown whenever there is a bug in the programs logic
     */
    public TypeOneException(String message) {
        super(message);
    }
}
