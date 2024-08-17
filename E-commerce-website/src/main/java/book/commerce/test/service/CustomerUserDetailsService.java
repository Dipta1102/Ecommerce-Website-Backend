package book.commerce.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import book.commerce.test.entity.User;
import book.commerce.test.model.CustomerUserDetails;
import book.commerce.test.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username).orElse(null);
		if (user == null) {
			String msg = " UserName " + username + " Not Found ";
			System.out.println("----" + msg + "----");
			throw new UsernameNotFoundException(msg);

		}
		return new CustomerUserDetails(user);
	}

}
