package com.yaro.repository;

import com.yaro.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String>, CustomerRepo {
    Page<Customer> findByFirstName(String firstName, Pageable pageable);

    Page<Customer> findByAuthorsName(String name, Pageable pageable);
}
