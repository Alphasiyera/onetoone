package com.example.onetoone.repository;

import com.example.onetoone.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
}
