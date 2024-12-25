package com.onlineBookStore.service.impl;

import com.onlineBookStore.dto.BookDTO;
import com.onlineBookStore.exceptions.BookValidationException;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.Entity.Book;
import com.onlineBookStore.mapper.BookMapper;
import com.onlineBookStore.repository.BookRepository;
import com.onlineBookStore.service.BookService;

import java.util.ArrayList;
import java.util.List;
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
			throw new BookValidationException("Book not found with ID: " + id);
		}
	}

	@Override
	public BookDTO createBook(BookDTO bookDTO) {
		Book book = BookMapper.toEntity(bookDTO);
		book = bookRepository.save(book);
		return BookMapper.toDTO(book);
	}

	@Override
	public BookDTO isBookNameExist(String title) {
		return bookRepository.findByTitle(title).map(book -> {
			System.out.println("Before mapping to DTO: " + book);
			return BookMapper.toDTO(book);
		}).orElseThrow(() -> new BookValidationException("Book not found with title: " + title));
	}

	@Override
	public void deleteBook(Long id) {
		Book book = bookRepository.findById(id).orElse(null);
		if (book != null) {
			bookRepository.delete(book);
		} else {
			throw new UserValidationException("User with ID " + id + " not found");
		}

	}

	@Override
	public boolean isBookExist(String title) {
		// TODO Auto-generated method stub
		boolean res = bookRepository.existsByTitle(title);

		return res;
	}

	@Override
	public List<BookDTO> getAllBooks() {
		List<Book> allBooks = bookRepository.findAll();
		List<BookDTO> bookDTO = new ArrayList<>();
		for (Book book : allBooks) {
			bookDTO.add(BookMapper.toDTO(book));
		}
		return bookDTO;
	}

	@Override
	public BookDTO updateBookId(Long id, BookDTO bookDTO) {

		Book existingBook = bookRepository.findById(id).orElse(null);
		if (existingBook != null) {
			existingBook.setTitle(bookDTO.getTitle());
			existingBook.setAuthor(bookDTO.getAuthor());
			existingBook.setPrice(bookDTO.getPrice());
			existingBook.setStock(bookDTO.getStock());
			bookRepository.save(existingBook);
			return BookMapper.toDTO(existingBook);
		}

		else {
			throw new BookValidationException("Book not found");
		}
	}

}
