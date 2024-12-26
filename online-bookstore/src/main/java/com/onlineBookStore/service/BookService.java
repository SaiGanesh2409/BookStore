package com.onlineBookStore.service;

import java.util.List;

import com.onlineBookStore.dto.BookDTO;

public interface BookService {
	BookDTO getBookById(Long id);

	BookDTO createBook(BookDTO bookDTO);

	BookDTO isBookNameExist(String title);

	List<BookDTO> getAllBooks();

	void deleteBook(Long id);

	boolean isBookExist(String title);

	BookDTO updateBookId(Long id, BookDTO bookDTO);
}
