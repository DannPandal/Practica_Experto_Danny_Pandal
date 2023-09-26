package com.devp.practice2Semana9.service.Implement;

import com.devp.practice2Semana9.exception.ExceptionNotFoundEntity;
import com.devp.practice2Semana9.model.Person;
import com.devp.practice2Semana9.repository.BookRepository;
import com.devp.practice2Semana9.repository.PersonRepository;
import com.devp.practice2Semana9.service.PersonService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImplement implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImplement(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Optional<Person> getPersonById(Long id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person;
        } else {
            throw new ExceptionNotFoundEntity("Person not found");
        }
    }

    @Override
    public Person savePerson(Person product) {
        return personRepository.save(product);
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Person updatePerson(Long id, Person person) {
        if (personRepository.existsById(id)){
            Person existingPerson = getPersonById(id).get();
            existingPerson.setId(id);
            existingPerson.setName(person.getName());
            return personRepository.save(existingPerson);
        }
        throw new ExceptionNotFoundEntity("Person not found");
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public String deletePerson(Long id) {
        if (personRepository.existsById(id)){
            personRepository.deleteById(id);
            return "Person removed";
        }
        throw new ExceptionNotFoundEntity("Person not found");
    }
}
