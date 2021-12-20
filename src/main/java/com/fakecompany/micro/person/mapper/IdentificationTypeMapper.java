package com.fakecompany.micro.person.mapper;


import com.fakecompany.micro.person.dto.IdentificationTypeDto;
import com.fakecompany.micro.person.model.IdentificationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentificationTypeMapper extends EntityMapper<IdentificationTypeDto, IdentificationType>{
}


