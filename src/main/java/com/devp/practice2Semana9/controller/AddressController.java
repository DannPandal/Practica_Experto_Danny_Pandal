package com.devp.practice2Semana9.controller;

import com.devp.practice2Semana9.model.Address;
import com.devp.practice2Semana9.service.Implement.AddressServiceImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address/")
public class AddressController {
    private final AddressServiceImplement addressServiceImplement;

    public AddressController(AddressServiceImplement addressServiceImplement) {
        this.addressServiceImplement = addressServiceImplement;
    }

    @GetMapping()
    public List<Address> getListAllAddress(){
        return addressServiceImplement.getAllAddress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){

        return addressServiceImplement.getAddressById(id)
                .map(Address -> new ResponseEntity<>(Address, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Address> createAddress(@RequestBody Address Address){
        Address AddressCreated = addressServiceImplement.saveAddress(Address);
        return new ResponseEntity<>(AddressCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address Address ){
        Address AddressUpdated = addressServiceImplement.updateAddress(id, Address);
        return ResponseEntity.ok(AddressUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        String message = addressServiceImplement.deleteAddress(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
