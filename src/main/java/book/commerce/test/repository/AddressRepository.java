package book.commerce.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.commerce.test.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
