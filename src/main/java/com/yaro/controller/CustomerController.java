package com.yaro.controller;

import com.yaro.domain.Author;
import com.yaro.domain.Customer;
import com.yaro.domain.dto.CustomerUpdate;
import com.yaro.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @PostMapping
    public int saveAll(@RequestBody List<Customer> customers) {
        customerRepository.saveAll(customers);
        return customers.size();
    }

    @PutMapping
    public Customer update(@RequestBody CustomerUpdate customer) {
        return customerRepository.update(customer);
    }

    @GetMapping
    public Iterable<Customer> getAll(@PageableDefault Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @GetMapping("/first-name")
    public Page<Customer> getByFirstName(@RequestParam String firstName, @PageableDefault Pageable pageable) {
        return customerRepository.findByFirstName(firstName, pageable);
    }

    @GetMapping("/author/name")
    public Page<Customer> getByAuthorName(@RequestParam String name, @PageableDefault Pageable pageable) {
        return customerRepository.findByAuthorsName(name, pageable);
    }

    @GetMapping("/author/name/pattern")
    public SearchHits<Customer> getByPatternAuthorName(@RequestParam String pattern) {
        return customerRepository.findByAuthorsNameByPattern(pattern);
    }

    @GetMapping("/test")
    public Iterable<Customer> initData() {
        Customer customer = Customer.builder()
                .firstName("Y").age(22).authors(asList(new Author("John"), new Author("Martin")))
                .build();

        customerRepository.save(customer);

        return customerRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return noContent().build();
        }
        return notFound().build();
    }
}
