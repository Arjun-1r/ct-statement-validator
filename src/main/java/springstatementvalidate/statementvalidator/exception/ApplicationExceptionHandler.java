package springstatementvalidate.statementvalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	// by AOP annotation Controller advice various exceptions are handled here.

	@ExceptionHandler(value = FileNotSupportedException.class)
	public ResponseEntity<String> handleUpdateViolationException(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<String> handleValidationException(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = InvalidColumnLengthException.class)
	public ResponseEntity<String> handleInvalidColumnsLengthException(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = Exception.class) // to handle general exceptions.
	public ResponseEntity<String> handleCommonExceptions(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
	
}
