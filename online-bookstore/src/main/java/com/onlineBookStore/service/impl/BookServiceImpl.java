package com.onlineBookStore.service.impl;

import com.onlineBookStore.dto.BookDTO;
import com.onlineBookStore.exceptions.BookValidationException;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.Entity.Book;
import com.onlineBookStore.Entity.User;
import com.onlineBookStore.mapper.BookMapper;
import com.onlineBookStore.mapper.UserMapper;
import com.onlineBookStore.repository.BookRepository;
import com.onlineBookStore.service.BookService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookDTO getBookById(Long id) {

		Optional<Book> userOptional = bookRepository.findById(id);
		if (userOptional.isPresent()) {
			return BookMapper.toDTO(userOptional.get());
		} else {
			throw new BookValidationException("Book not found");
		}
	}

	@Override
	public BookDTO createBook(BookDTO bookDTO) {
		Book book = BookMapper.toEntity(bookDTO);
		book = bookRepository.save(book);
		return BookMapper.toDTO(book);
	}
}
