package com.home.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Getter
@AllArgsConstructor
public class Author {

    @Field(type = Text)
    private String name;
}