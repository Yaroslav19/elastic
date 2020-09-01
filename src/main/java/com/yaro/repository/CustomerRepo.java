package com.yaro.repository;

import com.yaro.domain.Customer;
import com.yaro.domain.dto.CustomerUpdate;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface CustomerRepo {
    SearchHits<Customer> findByAuthorsNameByPattern(String firstName);

    Customer update(CustomerUpdate dto);
}