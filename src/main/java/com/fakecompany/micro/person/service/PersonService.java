package com.fakecompany.micro.person.service;


import com.fakecompany.micro.person.exception.DataConstraintViolationException;
import com.fakecompany.micro.person.exception.DataDuplicatedException;
import com.fakecompany.micro.person.exception.DataNotFoundException;
import com.fakecompany.micro.person.exception.ObjectNoEncontradoException;
import com.fakecompany.micro.person.model.Person;
import com.fakecompany.micro.person.model.PersonRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PersonService{
    private PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person){
        if(Objects.nonNull(person.getId())){
            Optional<Person> personOptional = personRepository.findById(person.getId());
            if(personOptional.isPresent()){
                throw new DataDuplicatedException("exception.data_duplicated.person");
            }
        }

        try {
            return personRepository.save(person);
        }catch (DataIntegrityViolationException e) {
            throw new DataConstraintViolationException("exception.data_constraint_violation.person");
        }
    }

    public Person editPerson(Person person){
        if(Objects.isNull(person.getId())){
            throw new ObjectNoEncontradoException("exception.objeto_no_encontrado");
        }

        System.out.println("ID: ##"+person.getId());
        Person personTransaction = personRepository.findById(person.getId())
                .orElseThrow(()-> new DataNotFoundException("exception.data_not_found.person"));

        personTransaction.setName(person.getName());
        personTransaction.setLastName(person.getLastName());
        personTransaction.setIdentification(person.getIdentification());
        personTransaction.setIdentificationTypeId(person.getIdentificationTypeId());
        personTransaction.setAge(person.getAge());
        personTransaction.setCityBirth(person.getCityBirth());

        return personTransaction;
    }

    public void deletePerson(Integer personId){
        if(Objects.nonNull(personId)){
            Optional<Person> productOptional = personRepository.findById(personId);
            if(!productOptional.isPresent()){
                throw new DataNotFoundException("exception.data_not_found.person");
            }
        }

        personRepository.deleteById(personId);
    }

    public List<Person> findAll(){
        List<Person> personList = personRepository.findAll();
        if (personList.isEmpty()){
            throw new DataNotFoundException("exception.data_not_found.person");
        }
        return personList;
    }

    public Person findById(Integer id){
        if(Objects.isNull(id)){
            throw new ObjectNotFoundException(id, "exception.objeto_no_encontrado");
        }
        return personRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("exception.data_not_found.person"));
    }

    public List<Person> findByAgeGreaterThanEqual(Integer age){
        List<Person> personList = personRepository.findByAgeGreaterThanEqual(age);
        if (personList.isEmpty()){
            throw new DataNotFoundException("exception.data_not_found.person");
        }
        return personList;
    }

    public List<Person> findByAgeLessThanEqual(Integer age){
        List<Person> personList = personRepository.findByAgeLessThanEqual(age);
        if (personList.isEmpty()){
            throw new DataNotFoundException("exception.data_not_found.person");
        }
        return personList;
    }







}
