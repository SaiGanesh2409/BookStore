package com.onlineBookStore.service.impl;

import com.onlineBookStore.dto.BookDTO;
import com.onlineBookStore.Entity.Book;
import com.onlineBookStore.mapper.BookMapper;
import com.onlineBookStore.repository.BookRepository;
import com.onlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return BookMapper.toDTO(book);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return BookMapper.toDTO(book);
    }
}
