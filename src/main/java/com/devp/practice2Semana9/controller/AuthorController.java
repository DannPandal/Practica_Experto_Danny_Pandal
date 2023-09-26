package com.devp.practice2Semana9.controller;

import com.devp.practice2Semana9.model.Author;
import com.devp.practice2Semana9.service.Implement.AuthorServiceImplement;
import com.devp.practice2Semana9.service.Implement.AuthorServiceImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author/")
public class AuthorController {
    private final AuthorServiceImplement authorServiceImplement;

    public AuthorController(AuthorServiceImplement authorServiceImplement) {
        this.authorServiceImplement = authorServiceImplement;
    }

    @GetMapping()
    public List<Author> getListAllAuthors(){
        return authorServiceImplement.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){

        return authorServiceImplement.getAuthorById(id)
                .map(Author -> new ResponseEntity<>(Author, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Author> createAuthor(@RequestBody Author Author){
        Author AuthorCreated = authorServiceImplement.saveAuthor(Author);
        return new ResponseEntity<>(AuthorCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author Author ){
        Author AuthorUpdated = authorServiceImplement.updateAuthor(id, Author);
        return ResponseEntity.ok(AuthorUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        String message = authorServiceImplement.deleteAuthor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
