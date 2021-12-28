package com.fakecompany.micro.person.facade;


import com.fakecompany.micro.person.adapter.client.ImageClient;
import com.fakecompany.micro.person.dto.ImageDto;
import com.fakecompany.micro.person.dto.PersonDto;
import com.fakecompany.micro.person.dto.PersonImageDto;
import com.fakecompany.micro.person.exception.ImageNotComeBodyException;
import com.fakecompany.micro.person.mapper.PersonMapper;
import com.fakecompany.micro.person.service.FileStoreService;
import com.fakecompany.micro.person.service.PersonService;
import com.fakecompany.micro.person.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fakecompany.micro.person.util.ModelMapperUtil.getMapperPersonaImageDto2PersonDto;
import static com.fakecompany.micro.person.util.OptionalFieldValidator.imageFileComeOnBody;
import static com.fakecompany.micro.person.util.OptionalFieldValidator.imageIdComeOnBody;


@Service
@Transactional
@AllArgsConstructor
public class PersonFacade {

    private PersonService personService;
    private PersonMapper personMapper;
    private FileStoreService fileStoreService;
    private ModelMapper modelMapper;
    private ImageClient imageClient;


    public PersonImageDto createPerson(PersonImageDto personImageDto, MultipartFile imagePart){
        PersonDto personDto = setUpMappingPersonaImageDto2PersonDto(personImageDto);
        personDto = personMapper.toDto(personService.createPerson(personMapper.toEntity(personDto)));
        personImageDto.setPersonId(personDto.getId());

        if (!imagePart.isEmpty()){
            ImageDto imageDtoS3 = storeNewImage(imagePart.getOriginalFilename(), personImageDto.getPersonId(),imagePart);
            personImageDto.setImageId(imageDtoS3.getId());
            personImageDto.setImageUrl(imageDtoS3.getImageUrl());
        }

        return personImageDto;
    }

    public PersonImageDto editPerson(PersonImageDto personImageDto, MultipartFile imagePart){
        PersonDto personDto = setUpMappingPersonaImageDto2PersonDto(personImageDto);
        personDto = personMapper.toDto(personService.editPerson(personMapper.toEntity(personDto)));

        ImageDto imageToEdit = imageClient.findByPersonId(personImageDto.getPersonId()).getBody();
        PersonImageDto personImageDtoEdit = modelMapper.map(personDto, PersonImageDto.class);

        if (imageFileComeOnBody(imagePart)){
            final String nameNewImage = imagePart.getOriginalFilename();
            if(!imageToEdit.getImageUrl().isEmpty()){
                if(imageIdComeOnBody(personImageDto.getImageId())&&
                        isOwnImage(imageToEdit.getId(), personImageDto.getImageId())){

                    imageToEdit = replaceImage(imageToEdit.getImageName(),nameNewImage, personImageDto.getImageId(),
                            personImageDto.getPersonId(),imagePart);
                }else {
                    throw new ImageNotComeBodyException("exception.not_come_body.image");
                }
            }else{
                imageToEdit = storeNewImage(nameNewImage,personImageDto.getPersonId(),imagePart);
            }
        }

        personImageDtoEdit.setPersonId(personDto.getId());
        personImageDtoEdit.setImageId(imageToEdit.getId());
        personImageDtoEdit.setImageUrl(imageToEdit.getImageUrl());

        return personImageDtoEdit;
    }

    public void deletePerson(Integer personId){
        final ImageDto image = imageClient.findByPersonId(personId).getBody();
        if(!image.getImageUrl().isEmpty()){
            imageClient.deleteImage(image.getId());
            fileStoreService.deleteFile(image.getImageName(),personId);
        }
        personService.deletePerson(personId);
    }

    public List<PersonImageDto> findAll(){
        final List<PersonImageDto> personImageDtoList = new ArrayList<>();
        final List<PersonDto> personDtoList = personMapper.toDto(personService.findAll());
        final List<ImageDto> imageList = imageClient.findAll().getBody();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        personDtoList.forEach(personDto -> System.out.println(personDto.toString()));

        personDtoList.forEach(personDto -> {
            PersonImageDto personImageDto = modelMapper.map(personDto, PersonImageDto.class);
            personImageDto.setPersonId(personDto.getId());
            Optional<ImageDto> imageOptional =  imageList.stream()
                    .filter(image -> image.getPersonId().equals(personDto.getId())).findAny();
            if(imageOptional.isPresent()){
                personImageDto.setImageId(imageOptional.get().getId());
                personImageDto.setImageUrl(imageOptional.get().getImageUrl());
            }
            personImageDtoList.add(personImageDto);
        });

        return personImageDtoList;
    }

    public List<PersonDto> findByAgeGreaterThanEqual(Integer age){
        return personMapper.toDto(personService.findByAgeGreaterThanEqual(age));
    }

    public List<PersonDto> findByAgeLessThanEqual(Integer age){
        return personMapper.toDto(personService.findByAgeLessThanEqual(age));
    }

    private ImageDto storeNewImage(final String nameNewImage, Integer personId, MultipartFile imagePart){
        ImageDto imageS3 = fileStoreService.createFile(nameNewImage, personId,imagePart);
        StandardResponse<ImageDto> imageDtoStandardResponse = imageClient.createImage(imageS3);

        return imageDtoStandardResponse.getBody();

    }

    private ImageDto replaceImage(String nameOldImage, String nameNewImage, String imageId,Integer personId,
                                  MultipartFile imagePart){

        fileStoreService.deleteFile(nameOldImage, personId);
        ImageDto imageS3 = fileStoreService.createFile(nameNewImage, personId,imagePart);
        imageS3.setId(imageId);
        StandardResponse<ImageDto> imageDtoStandardResponse = imageClient.editImage(imageS3);

        return imageDtoStandardResponse.getBody();
    }

    private PersonDto setUpMappingPersonaImageDto2PersonDto(PersonImageDto personImageDto){
        modelMapper= getMapperPersonaImageDto2PersonDto();
        PersonDto personDto = modelMapper.map(personImageDto, PersonDto.class);
        return personDto;
    }

    private boolean isOwnImage(String imageIdBd, String imageIdRequest){
        return imageIdBd.equals(imageIdRequest);
    }


}
