package book.commerce.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.commerce.test.entity.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {

}
