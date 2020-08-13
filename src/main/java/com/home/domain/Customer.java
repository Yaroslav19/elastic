package com.home.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "home", type = "customer"/*, shards = 2*/)
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;
}
