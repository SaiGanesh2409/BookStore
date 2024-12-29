package com.onlineBookStore.controller;

import com.onlineBookStore.dto.BookDTO;
import com.onlineBookStore.exceptions.BookValidationException;
import com.onlineBookStore.service.BookService;
import com.onlineBookStore.validation.BookValidation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.getBookById(id));
	}

	@PostMapping	
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
		List<String> errorMessages = bookValidation.validate(bookDTO);

		if (!errorMessages.isEmpty()) {
			throw new BookValidationException(String.join(", ", errorMessages));
		}

		BookDTO bookDto = bookService.createBook(bookDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BookDTO>> getAllUsers() {
		List<BookDTO> allUsers = new ArrayList<>();
		allUsers = bookService.getAllBooks();
		return ResponseEntity.ok(allUsers);
	}

	@GetMapping("title/{title}")
	public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title) {
		BookDTO bookDTO = bookService.isBookNameExist(title);
		return ResponseEntity.ok(bookDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.ok("Book with " + id + " Deleted");
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookDTO> updateBookid(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		BookDTO bookDto = bookService.updateBookId(id, bookDTO);
		return ResponseEntity.ok(bookDto);
	}

}
