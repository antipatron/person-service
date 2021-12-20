package com.fakecompany.micro.person.mapper;


import com.fakecompany.micro.person.model.Image;
import com.fakecompany.micro.person.dto.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {PersonMapper.class})
public interface ImageMapper extends EntityMapper<ImageDto, Image>{
}
