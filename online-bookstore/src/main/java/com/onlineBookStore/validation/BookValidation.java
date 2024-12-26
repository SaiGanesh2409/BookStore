package com.onlineBookStore.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineBookStore.dto.BookDTO;
import com.onlineBookStore.service.impl.BookServiceImpl;

@Component
public class BookValidation {

	@Autowired
	BookServiceImpl bookServiceImpl;

	public List<String> validate(BookDTO bookDTO) {
		List<String> errorMessages = new ArrayList<>();

		// Null check for bookDTO
		if (bookDTO == null) {
			errorMessages.add("BookDTO cannot be null");
			return errorMessages;
		}

		// Title validation
		String title = bookDTO.getTitle().trim();
		if (title.isEmpty()) {
			errorMessages.add("Title cannot be blank");
		} else if (title.length() < 3 || title.length() > 100) {
			errorMessages.add("Title must be between 3 and 100 characters");
		} else if (bookServiceImpl.isBookExist(title)) {
			errorMessages.add("Book with the title name already exists");
		}

		// Author validation
		String author = bookDTO.getAuthor().trim();
		if (author.isEmpty()) {
			errorMessages.add("Author cannot be blank");
		} else if (author.length() < 3 || author.length() > 50) {
			errorMessages.add("Author name must be between 3 and 50 characters");
		}

		// Price validation
		Double price = bookDTO.getPrice();
		if (price == null) {
			errorMessages.add("Price cannot be null");
		} else if (price <= 0) {
			errorMessages.add("Price must be greater than 0");
		} 

		// Stock validation
		Integer stock = bookDTO.getStock();
		if (stock == null) {
			errorMessages.add("Stock cannot be null");
		} else if (stock < 0) {
			errorMessages.add("Stock cannot be negative value");
		}

		return errorMessages;
	}
}
