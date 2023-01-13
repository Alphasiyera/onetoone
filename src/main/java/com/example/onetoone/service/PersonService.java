package com.example.onetoone.service;

import com.example.onetoone.entity.AddressEntity;
import com.example.onetoone.entity.PersonEntity;
import com.example.onetoone.model.AddressRequest;
import com.example.onetoone.model.PersonRequest;
import com.example.onetoone.model.PersonResponse;
import com.example.onetoone.repository.AddressRepository;
import com.example.onetoone.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PersonService {

    private final AddressRepository addressRepository;

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }


    public PersonResponse createPerson(PersonRequest personRequest, AddressRequest addressRequest) {

        AddressEntity address = new AddressEntity();
        address.setAddress1(personRequest.getAddress().getAddress1());
        address.setAddress2(personRequest.getAddress().getAddress2());
        address.setCity(personRequest.getAddress().getCity());
        address.setState(personRequest.getAddress().getState());
        address.setZipCode(personRequest.getAddress().getZipCode());
        addressRepository.save(address);


        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(personRequest.getFirstName());
        personEntity.setLastName(personRequest.getLastName());
        personEntity.setAddress(address);

        personRepository.save(personEntity);

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getId());

        return personResponse;
    }



//    @Cacheable(cacheNames = "persons",key="#personId")
    public PersonRequest getPerson(Long personId) {
        AddressRequest addressRequest = new AddressRequest();
        PersonRequest personRequest1 = new PersonRequest();

        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        Optional<AddressEntity> addressEntity = addressRepository.findById(personEntity.get().getAddress().getAddressId());
        if (personEntity.isPresent() && addressEntity.isPresent()) {
//          log.info("person " + personEntity.get().getAddress().getPerson() + " is found into the database...............");

            addressRequest.setAddress1(addressEntity.get().getAddress1());
            addressRequest.setAddress2(addressEntity.get().getAddress2());
            addressRequest.setCity(addressEntity.get().getCity());
            addressRequest.setState(addressEntity.get().getState());
            addressRequest.setZipCode(addressEntity.get().getZipCode());
            personRequest1.setFirstName(personEntity.get().getFirstName());
            personRequest1.setLastName(personEntity.get().getLastName());
            personRequest1.setAddress(addressRequest);
        } else {
//            log.info("person " + personEntity.get().getPersonId() + " is not found into the database...............");
        }
        return personRequest1;

    }


//    @CachePut(cacheNames = "persons",key="#personId")
public PersonResponse updatePerson(Long personId, PersonRequest personRequest) {
        PersonEntity personEntity =personRepository.findById(personId).get();

            AddressEntity addressEntity = addressRepository.findById(personEntity.getAddress().getAddressId()).get();

            addressEntity.setAddress1(personRequest.getAddress().getAddress1());
            addressEntity.setAddress2(personRequest.getAddress().getAddress2());
            addressEntity.setCity(personRequest.getAddress().getCity());
    addressEntity.setState(personRequest.getAddress().getState());
    addressEntity.setZipCode(personRequest.getAddress().getZipCode());
            addressRepository.save(addressEntity);

            personEntity.setFirstName(personRequest.getFirstName());
            personEntity.setLastName(personRequest.getLastName());

            personEntity.setAddress(addressEntity);

            personRepository.save(personEntity);
    PersonResponse personResponse =  new PersonResponse();
    personResponse.setId(personEntity.getId());
    return personResponse;


    }

//    @CacheEvict(cacheNames = "persons",key="#personId")
    public void deletePerson(Long personId)
    {
        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        if (personEntity.isPresent()) {
            personRepository.deleteById(personId);
            addressRepository.deleteById(personEntity.get().getAddress().getAddressId());
        } else {
            log.info("person " + personId + " is not found in database ..............");
        }

    }

//    @Cacheable(cacheNames = "persons")
    public List<PersonEntity> getAllPerson() {

        List<PersonEntity> personEntityList= personRepository.findAll();
        return personEntityList;
    }
}