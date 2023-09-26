package com.devp.practice2Semana9.service;

import com.devp.practice2Semana9.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> getAllAuthors();

    public Optional<Author> getAuthorById(Long id);

    public Author saveAuthor(Author author);

    public Author updateAuthor(Long id, Author author);

    public String deleteAuthor(Long id);
}
