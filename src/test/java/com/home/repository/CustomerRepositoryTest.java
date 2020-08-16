package com.home.repository;

import com.home.domain.Author;
import com.home.domain.Customer;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRepositoryTest {

    @ClassRule
    public static ElasticsearchContainer container = new ElasticsearchContainer();
    @Autowired
    CustomerRepository repository;

    @BeforeClass
    public static void before() {
        System.setProperty("spring.data.elasticsearch.cluster-nodes",
                container.getContainerIpAddress() + ":" + container.getMappedPort(9300));
    }

    @Test
    void testSave() {
        Customer customer = Customer.builder()
                .id("1")
                .firstName("Y")
                .age(22)
                .authors(asList(new Author("John"), new Author("Martin")))
                .build();
        customer = repository.save(customer);
        assertNotNull(customer);
    }

    @Test
    void testFindAll() {
        Iterable<Customer> customers = repository.findAll();
        assertTrue(customers.iterator().hasNext());
    }

    @Test
    void testFindByAuthor() {
        Page<Customer> customers = repository.findByAuthorsName("Martin", PageRequest.of(0, 5));
        assertFalse(customers.isEmpty());
    }

    @Test
    void testFindByFirstName() {
        Page<Customer> customers = repository.findByFirstName("Y", PageRequest.of(0, 5));
        assertFalse(customers.isEmpty());
    }
}