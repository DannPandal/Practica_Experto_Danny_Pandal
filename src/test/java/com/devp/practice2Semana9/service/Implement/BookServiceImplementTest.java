package com.devp.practice2Semana9.service.Implement;

import com.devp.practice2Semana9.exception.ExceptionNotFoundEntity;
import com.devp.practice2Semana9.model.Book;
import com.devp.practice2Semana9.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BookServiceImplementTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks    // Inyecta los mocks en el objeto a testear (donde estan los metodos a testear)
    BookServiceImplement bookServiceImplement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookServiceImplement = new BookServiceImplement(bookRepository);
    }

    // test success case get all book
    @Test
    void getAllSuccessInBook() {
        //Arrange
        Book book = new Book(1L,"Book 1", null);

        when(bookRepository.findAll()).thenReturn(List.of(book));

        //Act
        List<Book> books = bookServiceImplement.getAllBooks();

        //Assert
        assertFalse(books.isEmpty());
    }

    // test success case get book
    @Test
    void getSuccessInBook() {
        //Arrange
        Long id = 1L;
        Book book = new Book(1L,"Book 1", null);

        when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));

        //Act
        Optional<Book> bookGet = bookServiceImplement.getBookById(id);

        //Assert
        assertFalse(bookGet.isEmpty());
        assertEquals(bookGet.get().getTitle(), book.getTitle());
        assertEquals(bookGet.get().getAuthors(), book.getAuthors());
    }
    
    // test not found case get book
    @Test
    void getNotFoundInbook() {
        //Arrange
        Long id = 1L;

        when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //Act
        //Assert
        assertThrows(ExceptionNotFoundEntity.class, () -> bookServiceImplement.getBookById(id));
    }

    
    
    // test success case save book
    @Test
    void saveSuccessInBook() {
        //Arrange
        Book book = new Book(1L,"Book 1", null);

        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        //Act
        Book bookSaved = bookServiceImplement.saveBook(book);

        //Assert
        assertEquals(bookSaved.getTitle(), book.getTitle());
        assertEquals(bookSaved.getAuthors(), book.getAuthors());
    }

    // test success case update book
    @Test
    void updateSuccessInBook() {
        //Arrange
        Book book = new Book(1L,"Book 1", null);
        Book bookUpdate = new Book(1L,"Book 1", null);

        when(bookRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bookUpdate));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(bookUpdate);

        //Act
        Book bookUpdated = bookServiceImplement.updateBook(1L, book);

        //Assert
        assertEquals(bookUpdated.getId(), book.getId());
        assertEquals(bookUpdated.getTitle(), book.getTitle());
        assertEquals(bookUpdated.getAuthors(), book.getAuthors());

    }
    // test not found case update book
    @Test
    void updateNotFoundInBook() {
        //Arrange
        Long id = 1L;
        Book book = null;

        when(bookRepository.existsById(Mockito.anyLong())).thenReturn(false);

        //Act
        //Assert
        assertThrows(ExceptionNotFoundEntity.class, () -> bookServiceImplement.updateBook(id, book));
    }

    // test success case delete book
    @Test
    void deleteSuccessInBook() {
        //Arrange
        Long id = 1L;

        when(bookRepository.existsById(Mockito.anyLong())).thenReturn(true);
        doNothing().when(bookRepository).deleteById(Mockito.anyLong());

        //Act
        String bookDeleted = bookServiceImplement.deleteBook(id);

        //Assert
        assertEquals(bookDeleted, "Book removed");
    }

    // test not found case delete book
    @Test
    void deleteNotFoundInBook() {
        //Arrange
        Long id = 1L;

        when(bookRepository.existsById(Mockito.anyLong())).thenReturn(false);

        //Act
        //Assert
        assertThrows(ExceptionNotFoundEntity.class, () -> bookServiceImplement.deleteBook(id));
    }

}