package com.devp.practice2Semana9.controller;

import com.devp.practice2Semana9.model.Person;
import com.devp.practice2Semana9.service.Implement.PersonServiceImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/person/")
public class PersonController {

    private final PersonServiceImplement personServiceImplement;

    public PersonController(PersonServiceImplement personServiceImplement) {
        this.personServiceImplement = personServiceImplement;
    }

    @GetMapping()
    public List<Person> getListAllPersons(){
        return personServiceImplement.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
//        Person PersonSearch = personServiceImplement.getPersonById(id);
//        return ResponseEntity.ok(PersonSearch);
        return personServiceImplement.getPersonById(id)
                .map(Person -> new ResponseEntity<>(Person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Person> createPerson(@RequestBody Person Person){
        Person PersonCreated = personServiceImplement.savePerson(Person);
        return new ResponseEntity<>(PersonCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person Person ){
        Person PersonUpdated = personServiceImplement.updatePerson(id, Person);
        return ResponseEntity.ok(PersonUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        String message = personServiceImplement.deletePerson(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
