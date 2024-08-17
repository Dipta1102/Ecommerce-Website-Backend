package book.commerce.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.commerce.test.entity.User;
import book.commerce.test.jwt.JwtUtil;
import book.commerce.test.model.JwtRequest;
import book.commerce.test.model.JwtResponse;
import book.commerce.test.repository.UserRepository;
import book.commerce.test.service.CustomerUserDetailsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class JwtController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerUserDetailsService customUserDetailsService;

	@GetMapping("/")
	public String test() {
		String msg = "JWT-REST-API server is running";
		return msg;
	}

	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
		String username = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			System.out.println("----- " + e.getMessage() + " -----");
			return ResponseEntity.status(401).body("Bad Credential");
		}

		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		User user = userRepository.findById(username).orElse(null);
		String jwt = jwtUtil.generateToken(userDetails);
		System.out.println("----- JWT: " + jwt + " -----");
		return ResponseEntity.status(201).body(JwtResponse.builder().user(user).jwt(jwt).build());
	}

}
