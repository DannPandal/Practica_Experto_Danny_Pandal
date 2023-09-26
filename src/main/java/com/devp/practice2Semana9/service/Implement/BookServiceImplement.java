package com.devp.practice2Semana9.service.Implement;

import com.devp.practice2Semana9.exception.ExceptionNotFoundEntity;
import com.devp.practice2Semana9.model.Book;
import com.devp.practice2Semana9.model.Person;
import com.devp.practice2Semana9.repository.BookRepository;
import com.devp.practice2Semana9.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplement implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImplement(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Optional<Book> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new ExceptionNotFoundEntity("Book not found");
        }
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)){
            Book existingBook = getBookById(id).get();
            existingBook.setId(id);
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthors(book.getAuthors());
            return bookRepository.save(existingBook);
        }
        throw new ExceptionNotFoundEntity("Book not found");
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public String deleteBook(Long id) {
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return "Book removed";
        }
        throw new ExceptionNotFoundEntity("Book not found");
    }
}
