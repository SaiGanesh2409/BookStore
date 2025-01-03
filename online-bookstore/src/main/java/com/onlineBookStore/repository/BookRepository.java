package com.onlineBookStore.repository;

import com.onlineBookStore.Entity.Book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitle(String title);

	boolean existsByTitle(String title);
	
	

}
