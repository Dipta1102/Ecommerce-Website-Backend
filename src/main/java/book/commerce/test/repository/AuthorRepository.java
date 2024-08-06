package book.commerce.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.commerce.test.entity.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

}
