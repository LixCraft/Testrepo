package fr.ing.interview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ing.interview.model.Customer;
import fr.ing.interview.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/consult")
    public List<Customer> customersAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/consult/{id}")
    public Customer customers(@PathVariable(value = "id") Long id) {
        return customerRepository.findByCustomerId(id).get(0);
    }
}
