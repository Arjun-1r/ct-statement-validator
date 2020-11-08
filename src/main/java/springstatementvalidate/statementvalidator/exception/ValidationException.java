package springstatementvalidate.statementvalidator.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 2523548180680962749L;

	public ValidationException(String message) {
		super(message);
	}
}
