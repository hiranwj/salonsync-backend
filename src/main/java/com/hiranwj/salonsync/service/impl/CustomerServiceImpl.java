package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.CustomerDto;
import com.hiranwj.salonsync.model.Customer;
import com.hiranwj.salonsync.repository.CustomerRepository;
import com.hiranwj.salonsync.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> insertCustomerData(CustomerDto customerDto) {
        try {
            Customer customer = new Customer();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setNicNumber(customerDto.getNicNumber());
            customer.setContactNumber(customerDto.getContactNumber());
            customer.setCreatedBy((int) (System.currentTimeMillis() / 1000));
            customerRepository.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
