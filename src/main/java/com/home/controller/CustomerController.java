package com.home.controller;

import com.home.domain.Customer;
import com.home.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @PostMapping
    public int save(@RequestBody List<Customer> customers) {
        customerRepository.saveAll(customers);
        return customers.size();
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(customerRepository.findAll());
    }

    @GetMapping("/first-name")
    public ResponseEntity findByFirstName(@RequestParam String firstName) {
        Page<Customer> customers = customerRepository.findByFirstName(firstName, PageRequest.of(0, 1));
        return ResponseEntity.ok().body(customers.getContent());
    }

}
