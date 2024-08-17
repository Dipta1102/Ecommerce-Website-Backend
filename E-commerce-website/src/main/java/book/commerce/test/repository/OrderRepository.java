package book.commerce.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.commerce.test.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
