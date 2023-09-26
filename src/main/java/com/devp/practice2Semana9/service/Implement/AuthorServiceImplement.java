package com.devp.practice2Semana9.service.Implement;

import com.devp.practice2Semana9.exception.ExceptionNotFoundEntity;
import com.devp.practice2Semana9.model.Author;
import com.devp.practice2Semana9.model.Author;
import com.devp.practice2Semana9.repository.AuthorRepository;
import com.devp.practice2Semana9.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplement implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImplement(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Optional<Author> getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author;
        } else {
            throw new ExceptionNotFoundEntity("Author not found");
        }
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Author updateAuthor(Long id, Author author) {
        if (authorRepository.existsById(id)){
            Author existingAuthor = getAuthorById(id).get();
            existingAuthor.setId(id);
            existingAuthor.setName(author.getName());
            return authorRepository.save(existingAuthor);
        }
        throw new ExceptionNotFoundEntity("Author not found");
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public String deleteAuthor(Long id) {
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return "Author removed";
        }
        throw new ExceptionNotFoundEntity("Author not found");
    }
}
