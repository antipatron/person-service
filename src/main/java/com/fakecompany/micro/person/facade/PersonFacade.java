package com.fakecompany.micro.person.facade;


import com.fakecompany.micro.person.adapter.client.ImageClient;
import com.fakecompany.micro.person.dto.ImageDto;
import com.fakecompany.micro.person.dto.PersonDto;
import com.fakecompany.micro.person.dto.PersonImageDto;
import com.fakecompany.micro.person.mapper.PersonMapper;
import com.fakecompany.micro.person.model.Person;
import com.fakecompany.micro.person.service.PersonService;
import com.fakecompany.micro.person.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.fakecompany.micro.person.util.ObjectTypeConverter.image2Base64;


@Service
@Transactional
public class PersonFacade {
    private PersonService personService;

    @Autowired
    private ImageClient imageClient;
    private PersonMapper personMapper;

    //TODO rafactorizar a I
    public PersonFacade(PersonService personService,
                        PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public PersonImageDto createPerson(PersonImageDto personImageDto, MultipartFile imagePart){
        Person person = mappingPerson(personImageDto);
        person = personService.createPerson(person);
        personImageDto.setPersonId(person.getId());

        if (!imagePart.isEmpty()){
            StandardResponse<ImageDto> image =  imageClient.createImage(imagePart, personImageDto.getPersonId());
            Logger.getGlobal().log(Level.INFO, image.toString());
            personImageDto.setImageId(image.getBody().getId());
        }

        return personImageDto;
    }



    public PersonImageDto editPerson(PersonImageDto personImageDto, MultipartFile imagePart){
/*
        Person person = mappingPerson(personImageDto);
        PersonDto personDto = personMapper.toDto(personService.editPerson(person));

        //TODO puedo refactorizar esta parte y juntarla con la parte del guardar.
        PersonImageDto personImageDtoEdit = new PersonImageDto();

        if (!imagePart.isEmpty()){
            //TODO comprobar que si tenga imagen en bd (restriccion es una persona una imagen)
            if(hasImage(personImageDto.getPersonId())){
                if(imageComeOnBody(personImageDto.getImageId())){
                    Image imageEdit = mappingImage(personImageDto.getImageId(), personImageDto.getPersonId(), imagePart);
                    imageEdit = imageService.editImage(imageEdit);
                    personImageDtoEdit.setImageId(imageEdit.getId());
                }else{
                    throw new ImageNotComeBodyException("exception.not_come_body.image");
                }

            }else{
                Image image = imageService.createImage(mappingImage(personImageDto.getImageId(),personImageDto.getPersonId(), imagePart));
                personImageDtoEdit.setImageId(image.getId());
            }
        }

        personImageDtoEdit.setPersonId(personDto.getId());
        personImageDtoEdit.setName(personDto.getName());
        personImageDtoEdit.setLastName(personDto.getLastName());
        personImageDtoEdit.setIdentification(personDto.getIdentification());
        personImageDtoEdit.setIdentificationTypeId(personDto.getIdentificationTypeId());
        personImageDtoEdit.setAge(personDto.getAge());
        personImageDtoEdit.setCityBirth(personDto.getCityBirth());

        return personImageDtoEdit;
        */
        return null;
    }

    private ImageDto mappingImage(String imageId, Integer personId, MultipartFile imagePart){
        ImageDto image = new ImageDto();
        image.setId(imageId);
        image.setImage(image2Base64(imagePart));
        image.setPersonId(personId);
        return image;
    }

    private Person mappingPerson(PersonImageDto personImageDto){
        Person person = new Person();
        person.setId(personImageDto.getPersonId());

        person.setName(personImageDto.getName());
        person.setLastName(personImageDto.getLastName());
        person.setIdentification(personImageDto.getIdentification());
        person.setIdentificationTypeId(personImageDto.getIdentificationTypeId());
        person.setAge(personImageDto.getAge());
        person.setCityBirth(personImageDto.getCityBirth());

        return person;

    }

   /* private boolean hasImage(Integer personId){
        return !imageService.findByPersonId(personId).getImage().isEmpty();
    }*/

    public void deletePerson(Integer personId){
       /* //TODO buscar si la persona tiene imagen, si es así, eliminar la imagen
        Image image = imageService.findByPersonId(personId);
        if(!image.getImage().isEmpty()){
            imageService.deleteImage(image.getId());
        }

        personService.deletePerson(personId);*/

    }

    public List<PersonImageDto> findAll(){

       /* List<PersonDto> personDtoList = personMapper.toDto(personService.findAll());
        List<PersonImageDto> personImageDtoList = new ArrayList<>();


        personDtoList.forEach(personDto -> {

            PersonImageDto personImageDto = new PersonImageDto();

            personImageDto.setPersonId(personDto.getId());
            personImageDto.setName(personDto.getName());
            personImageDto.setLastName(personDto.getLastName());
            personImageDto.setIdentification(personDto.getIdentification());
            personImageDto.setIdentificationTypeId(personDto.getIdentificationTypeId());
            personImageDto.setAge(personDto.getAge());
            personImageDto.setCityBirth(personDto.getCityBirth());
            personImageDto.setImageId(imageService.findByPersonId(personDto.getId()).getId());

            personImageDtoList.add(personImageDto);

        });



        return personImageDtoList;*/
        return null;


    }

    public List<PersonDto> findByAgeGreaterThanEqual(Integer age){
        return personMapper.toDto(personService.findByAgeGreaterThanEqual(age));
    }

    public List<PersonDto> findByAgeLessThanEqual(Integer age){
        return personMapper.toDto(personService.findByAgeLessThanEqual(age));
    }
}
