package com.onlineBookStore.controller;

import com.onlineBookStore.dto.BookDTO;
import com.onlineBookStore.exceptions.BookValidationException;
import com.onlineBookStore.service.BookService;
import com.onlineBookStore.validation.BookValidation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BookValidation bookValidation;

	@GetMapping("/{id}")
	public BookDTO getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}

	@PostMapping
	public BookDTO createBook(@RequestBody BookDTO bookDTO) {
		List<String> errorMessages = bookValidation.validate(bookDTO);

		if (!errorMessages.isEmpty()) {
			throw new BookValidationException(String.join(", ", errorMessages));
		}

		return bookService.createBook(bookDTO);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BookDTO>> getAllUsers() {
		List<BookDTO> allUsers = new ArrayList<>();
		allUsers = bookService.getAllBooks();
		return ResponseEntity.ok(allUsers);
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title) {
		BookDTO bookDTO = bookService.isBookNameExist(title);
		return ResponseEntity.ok(bookDTO);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		bookService.deleteBook(id);
		return "Book with id " + id + " deleted";
	}

	@PutMapping("{id}")
	public BookDTO updateBookid(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		return bookService.updateBookId(id, bookDTO);
	}

}
