package com.example.onetoone.repository;

import com.example.onetoone.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {


}
