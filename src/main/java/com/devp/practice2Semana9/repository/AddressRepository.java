package com.devp.practice2Semana9.repository;

import com.devp.practice2Semana9.model.Address;
import com.devp.practice2Semana9.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
