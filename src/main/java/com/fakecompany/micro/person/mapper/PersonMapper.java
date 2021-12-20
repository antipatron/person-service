package com.fakecompany.micro.person.mapper;

import com.fakecompany.micro.person.dto.PersonDto;
import com.fakecompany.micro.person.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {IdentificationTypeMapper.class})
public interface PersonMapper extends EntityMapper<PersonDto, Person>{
}
