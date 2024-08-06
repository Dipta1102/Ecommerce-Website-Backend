package book.commerce.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.commerce.test.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
