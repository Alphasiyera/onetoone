package com.example.onetoone.controller;

import com.example.onetoone.entity.PersonEntity;
import com.example.onetoone.model.AddressRequest;
import com.example.onetoone.model.PersonRequest;
import com.example.onetoone.model.PersonResponse;
import com.example.onetoone.repository.PersonRepository;
import com.example.onetoone.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class PersonController {
    public final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping(value = "/persons",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPersonProfile(@RequestBody PersonRequest personRequest ,AddressRequest addressRequest ){
       PersonResponse personResponse = personService.createPerson(personRequest,addressRequest);
       if (personResponse!=null){
           return new ResponseEntity<>(personResponse, HttpStatus.OK);
       }else {
           return new ResponseEntity<>(personResponse,HttpStatus.NO_CONTENT);
       }


    }
    @GetMapping(value="/persons/{personId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRequest> getPerson(@PathVariable Long personId) {
        PersonRequest personRequest = personService.getPerson(personId);
        log.info("person details fetched by id ");
        return new ResponseEntity<>(personRequest, HttpStatus.OK);
    }
    @GetMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonEntity>> getAllPerson() {
        List<PersonEntity> personEntity = personService.getAllPerson();

        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }
   @PutMapping(value="/persons/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long personId,@RequestBody PersonRequest personRequest){
        PersonResponse personResponse = personService.updatePerson(personId,personRequest);
        log.info("person Details Updated successfully");
        return new ResponseEntity<>(personResponse,HttpStatus.OK);
   }
    @DeleteMapping(value = "/persons/{personId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        log.info("Person details deleted");
        return ResponseEntity.ok().build();
    }


}
