package book.commerce.test.model;

import book.commerce.test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JwtResponse {
	private User user;
	private String jwt;
}
