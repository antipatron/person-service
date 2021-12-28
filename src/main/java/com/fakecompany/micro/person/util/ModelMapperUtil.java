package com.fakecompany.micro.person.util;

import com.fakecompany.micro.person.dto.PersonDto;
import com.fakecompany.micro.person.dto.PersonImageDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class ModelMapperUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static ModelMapper getMapperPersonaImageDto2PersonDto(){
        modelMapper.typeMap(PersonImageDto.class, PersonDto.class).addMappings(mapper->{
            mapper.map(PersonImageDto::getPersonId,PersonDto::setId);
            mapper.map(PersonImageDto::getIdentificationTypeId,PersonDto::setIdentificationTypeId);
        });

        return modelMapper;
    }


}
