package oop.ex6.parser;

import oop.ex6.validator.TypeOneException;

public class BadLineException extends TypeOneException {
	/**
	 * this exception is thrown whenever there is an error in the files parsing
	 */
	public BadLineException(String message) {
		super(message);
	}

}
