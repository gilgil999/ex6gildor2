package oop.ex6.parser;

public class BadLineException extends TypeOneException {
	/**
	 * this exception is thrown whenever there is an error in the files parsing
	 */
	public static String ERROR_MESSAGE = "there is a line with unknown formation";
	private String errorMessage;

	public BadLineException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	public BadLineException(){
		errorMessage = ERROR_MESSAGE;
	}
}
