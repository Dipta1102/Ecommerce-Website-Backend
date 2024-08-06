package book.commerce.test.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.model.ErrorResponse;

public class APIExceptionHandler {
	private static ErrorResponse errorResponse = new ErrorResponse();

	public static void updateErrorResponse() {
		errorResponse.setStatusCode(500L);
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setTimeStamp(LocalDateTime.now());

	}

	@ExceptionHandler(NoEntitiesException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleNoEntities(NoEntitiesException exception) {
		updateErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCause("no entities");
		return ResponseEntity.status(500).body(errorResponse);
	}

	@ExceptionHandler(EntityExistsException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleEntityExists(EntityExistsException exception) {
		updateErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCause("no entities");
		return ResponseEntity.status(500).body(errorResponse);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException exception) {
		updateErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCause("no entities");
		return ResponseEntity.status(500).body(errorResponse);
	}

}
