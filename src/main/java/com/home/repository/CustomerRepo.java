package com.home.repository;

import com.home.domain.Customer;
import com.home.domain.dto.CustomerUpdate;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface CustomerRepo {
    SearchHits<Customer> findByAuthorsNameByPattern(String firstName);

    Customer update(CustomerUpdate dto);
}