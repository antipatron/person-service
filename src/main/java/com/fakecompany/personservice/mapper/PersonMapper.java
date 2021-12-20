package com.fakecompany.personservice.mapper;

import com.fakecompany.personservice.dto.PersonDto;
import com.fakecompany.personservice.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {IdentificationTypeMapper.class})
public interface PersonMapper extends EntityMapper<PersonDto, Person>{
}
