package com.yaro.repository;

import com.yaro.domain.Customer;
import com.yaro.domain.dto.CustomerUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

@Repository
@RequiredArgsConstructor
public class CustomerRepoImpl implements CustomerRepo {

    private final ElasticsearchOperations operations;

    @Override
    public SearchHits<Customer> findByAuthorsNameByPattern(String pattern) {
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery("authors.name", format(".*%s.*", pattern)))
                .build();

        return operations.search(searchQuery, Customer.class, IndexCoordinates.of("home"));
    }

    @Override
    public Customer update(CustomerUpdate dto) {
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("firstName", dto.getFirstName()).minimumShouldMatch("75%"))
                .build();
        SearchHits<Customer> customers = operations.search(searchQuery, Customer.class, IndexCoordinates.of("home"));
        Customer customer = customers.getSearchHit(0).getContent();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setAge(dto.getAge());

        return operations.save(customer);
    }
}