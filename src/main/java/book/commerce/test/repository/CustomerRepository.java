package book.commerce.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.commerce.test.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
