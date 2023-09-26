package com.devp.practice2Semana9.controller;

import com.devp.practice2Semana9.model.Book;
import com.devp.practice2Semana9.service.Implement.BookServiceImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book/")
public class BookController {

    private final BookServiceImplement bookServiceImplement;

    public BookController(BookServiceImplement bookServiceImplement) {
        this.bookServiceImplement = bookServiceImplement;
    }

    @GetMapping()
    public List<Book> getListAllBooks(){
        return bookServiceImplement.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookServiceImplement.getBookById(id)
                .map(Book -> new ResponseEntity<>(Book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book bookCreated = bookServiceImplement.saveBook(book);
        return ResponseEntity.ok(bookCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book ){
        Book bookUpdated = bookServiceImplement.updateBook(id, book);
        return ResponseEntity.ok(bookUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        String message = bookServiceImplement.deleteBook(id);
        return ResponseEntity.ok(message);
    }

}
