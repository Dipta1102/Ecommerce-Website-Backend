package book.commerce.test.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	private long statusCode;
	private HttpStatus status;
	private LocalDateTime timeStamp;
	private String message;
	private String cause;
}
