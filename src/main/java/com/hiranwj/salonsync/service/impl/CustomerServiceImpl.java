package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.CustomerDto;
import com.hiranwj.salonsync.model.Customer;
import com.hiranwj.salonsync.repository.CustomerRepository;
import com.hiranwj.salonsync.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> insertCustomerData(CustomerDto customerDto) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findByNicNumber(customerDto.getNicNumber());
            if (existingCustomer.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer with this NIC number already exists.");
            }
            Customer customer = new Customer();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setNicNumber(customerDto.getNicNumber());
            customer.setContactNumber(customerDto.getContactNumber());
            customer.setUsername(customerDto.getUsername());
            customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            customer.setCreatedAt((int) (System.currentTimeMillis() / 1000));
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.OK).body("Customer saved successfully");
        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
