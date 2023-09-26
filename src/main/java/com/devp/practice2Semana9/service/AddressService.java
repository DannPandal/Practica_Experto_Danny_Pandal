package com.devp.practice2Semana9.service;

import com.devp.practice2Semana9.model.Address;
import com.devp.practice2Semana9.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    public List<Address> getAllAddress();

    public Optional<Address> getAddressById(Long id);

    public Address saveAddress(Address address);

    public Address updateAddress(Long id, Address address);

    public String deleteAddress(Long id);
}
