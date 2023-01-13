package com.example.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest implements Serializable {
    private String firstName;
    private String lastName;

    private AddressRequest address;
}
