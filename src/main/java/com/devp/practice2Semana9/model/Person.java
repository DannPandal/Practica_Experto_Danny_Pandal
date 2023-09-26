package com.devp.practice2Semana9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Address> address = new ArrayList<>();

    public Person() {
    }
    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
