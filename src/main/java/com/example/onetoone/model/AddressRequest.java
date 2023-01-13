package com.example.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressRequest implements Serializable {
private String address1;
private String address2;
private String city;
private String state;
private Long zipCode;

}
