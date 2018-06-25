package parser;

public class BadLineException extends TypeOneException {
	public static String ERROR_MESSAGE = "there is a line with unknown formation";
	private String errorMessage;

	public BadLineException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	public BadLineException(){
		errorMessage = ERROR_MESSAGE;
	}
}
