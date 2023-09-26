package com.devp.practice2Semana9.service;

import com.devp.practice2Semana9.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> getAllBooks();

    public Optional<Book> getBookById(Long id);

    public Book saveBook(Book book);

    public Book updateBook(Long id, Book book);

    public String deleteBook(Long id);
}
