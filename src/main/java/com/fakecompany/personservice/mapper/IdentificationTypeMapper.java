package com.fakecompany.personservice.mapper;


import com.fakecompany.personservice.dto.IdentificationTypeDto;
import com.fakecompany.personservice.model.IdentificationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentificationTypeMapper extends EntityMapper<IdentificationTypeDto, IdentificationType>{
}


