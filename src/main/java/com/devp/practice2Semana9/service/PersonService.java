package com.devp.practice2Semana9.service;

import com.devp.practice2Semana9.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    public List<Person> getAllPersons();

    public Optional<Person> getPersonById(Long id);

    public Person savePerson(Person person);

    public Person updatePerson(Long id, Person person);

    public String deletePerson(Long id);
}
