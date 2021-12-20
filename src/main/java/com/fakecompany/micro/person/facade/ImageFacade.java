package com.fakecompany.micro.person.facade;

import com.fakecompany.micro.person.model.Image;
import com.fakecompany.micro.person.dto.ImageDto;
import com.fakecompany.micro.person.mapper.ImageMapper;
import com.fakecompany.micro.person.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.fakecompany.micro.person.util.ObjectTypeConverter.image2Base64;

@Service
@Transactional
public class ImageFacade {

    private ImageService imageService;
    private ImageMapper imageMapper;

    public ImageFacade(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    public ImageDto createImage(ImageDto imageDto, MultipartFile imagePart){
        imageDto.setImage(image2Base64(imagePart));
        return imageMapper.toDto(imageService.createImage(imageMapper.toEntity(imageDto)));
    }

    public ImageDto editImage(ImageDto imageDto, MultipartFile imagePart){
        Image imageEdit = imageService.findById(imageDto.getId());
        imageEdit.setImage(image2Base64(imagePart));
        imageEdit.setPersonId(imageDto.getPersonId());

        ImageDto imageDtoEdit = imageMapper.toDto(imageService.editImage(imageEdit));

        return imageDtoEdit;
    }

    public void deleteImage(String imageId){
        imageService.deleteImage(imageId);

    }

    public List<ImageDto> findAll(){
        return imageMapper.toDto(imageService.findAll());
    }

}
