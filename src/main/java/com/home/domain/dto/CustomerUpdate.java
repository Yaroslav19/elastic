package com.home.domain.dto;

import lombok.Value;

@Value
public class CustomerUpdate {
    String firstName;
    String lastName;
    int age;
}