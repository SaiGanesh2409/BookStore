package com.onlineBookStore.service;

import com.onlineBookStore.dto.BookDTO;

public interface BookService {
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO bookDTO);
    // Other methods...
}
