package com.fakecompany.personservice.mapper;


import com.fakecompany.personservice.dto.ImageDto;
import com.fakecompany.personservice.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {PersonMapper.class})
public interface ImageMapper extends EntityMapper<ImageDto, Image>{
}
