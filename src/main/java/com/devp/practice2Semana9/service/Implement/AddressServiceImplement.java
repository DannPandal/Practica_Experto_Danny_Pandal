package com.devp.practice2Semana9.service.Implement;

import com.devp.practice2Semana9.exception.ExceptionNotFoundEntity;
import com.devp.practice2Semana9.model.Address;
import com.devp.practice2Semana9.model.Address;
import com.devp.practice2Semana9.repository.AddressRepository;
import com.devp.practice2Semana9.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplement implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImplement(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Optional<Address> getAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            return address;
        } else {
            throw new ExceptionNotFoundEntity("Address not found");
        }
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Address updateAddress(Long id, Address address) {
        if (addressRepository.existsById(id)){
            Address existingAddress = getAddressById(id).get();
            existingAddress.setId(id);
            existingAddress.setStreet(address.getStreet());
            existingAddress.setPerson(address.getPerson());
            return addressRepository.save(existingAddress);
        }
        throw new ExceptionNotFoundEntity("Address not found");
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public String deleteAddress(Long id) {
        if (addressRepository.existsById(id)){
            addressRepository.deleteById(id);
            return "Address removed";
        }
        throw new ExceptionNotFoundEntity("Address not found");
    }
}
