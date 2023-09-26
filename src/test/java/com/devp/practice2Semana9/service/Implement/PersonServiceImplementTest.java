package com.devp.practice2Semana9.service.Implement;

import com.devp.practice2Semana9.exception.ExceptionNotFoundEntity;
import com.devp.practice2Semana9.model.Book;
import com.devp.practice2Semana9.model.Person;
import com.devp.practice2Semana9.repository.PersonRepository;
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

class PersonServiceImplementTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks    // Inyecta los mocks en el objeto a testear (donde estan los metodos a testear)
    PersonServiceImplement personServiceImplement;

    @BeforeEach
    // Antes de cada test
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personServiceImplement = new PersonServiceImplement(personRepository);
    }


    // test success case get all person
    @Test
    void getAllSuccessInPerson() {
        //Arrange
        Person person = new Person(1L, "Danny");

        when(personRepository.findAll()).thenReturn(List.of(person));

        //Act
        List<Person> persons = personServiceImplement.getAllPersons();

        //Assert
        assertFalse(persons.isEmpty());
    }


    // test success case get person
    @Test
    void getSuccessInPerson() {
        //Arrange
        Long id = 1L;
        Person person = new Person(1L, "Danny");

        when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(person));

        //Act
        Optional<Person> personGet = personServiceImplement.getPersonById(id);

        //Assert
        assertFalse(personGet.isEmpty());
        assertEquals(personGet.get().getName(), person.getName());
    }

    // test not found case get person
    @Test
    void getNotFoundInPerson() {
        //Arrange
        Long id = 1L;

        when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //Act
        //Assert
        assertThrows(ExceptionNotFoundEntity.class, () -> personServiceImplement.getPersonById(id));
    }



    // test success case save person
    @Test
    void saveSuccessInPerson() {

        //Arrange
        Person person = new Person(1L, "Danny");

        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        //Act
        Person personSaved = personServiceImplement.savePerson(person);

        //Assert
        assertEquals(personSaved.getName(), person.getName());
    }
    // test success case update person
    @Test
    void updateSuccessInPerson() {
        //Arrange
        Person person = new Person(1L, "Danny");
        Person personUpdate = new Person(1L, "Danny Pandal");

        when(personRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personUpdate));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(personUpdate);

        //Act
        Person personUpdated = personServiceImplement.updatePerson(1L, person);

        //Assert
        assertEquals(personUpdated.getName(), person.getName());
    }
    // test not found case update person
    @Test
    void updateNotFoundInPerson() {
        //Arrange
        Long id = 1L;
        Person person = null;

        when(personRepository.existsById(Mockito.anyLong())).thenReturn(false);

        //Act
        //Assert
        assertThrows(ExceptionNotFoundEntity.class, () -> personServiceImplement.updatePerson(id, person));
    }

    // test success case delete person
    @Test
    void deleteSuccessInPerson() {
        //Arrange
        Long id = 1L;

        when(personRepository.existsById(Mockito.anyLong())).thenReturn(true);
        doNothing().when(personRepository).deleteById(Mockito.anyLong());

        //Act
        String personDeleted = personServiceImplement.deletePerson(id);

        //Assert
        assertEquals(personDeleted, "Person removed");
    }

    // test not found case delete person
    @Test
    void deleteNotFoundInPerson() {
        //Arrange
        Long id = 1L;

        when(personRepository.existsById(Mockito.anyLong())).thenReturn(false);

        //Act
        //Assert
        assertThrows(ExceptionNotFoundEntity.class, () -> personServiceImplement.deletePerson(id));
    }
}