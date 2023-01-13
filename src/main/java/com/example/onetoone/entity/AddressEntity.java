package com.example.onetoone.entity;

import com.fasterxml.jackson.databind.deser.Deserializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String address1;
    private String address2;
    private String city;
    private String State;
    private Long zipCode;

    @OneToOne(mappedBy = "address")
    private PersonEntity person;
}
