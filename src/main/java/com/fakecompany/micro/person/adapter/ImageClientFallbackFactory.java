package com.fakecompany.micro.person.adapter;

import com.fakecompany.common.dto.ImageDto;
import com.fakecompany.common.util.StandardResponse;
import com.fakecompany.micro.person.adapter.client.ImageClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ImageClientFallbackFactory implements ImageClient {

    @Override
    public StandardResponse<ImageDto> createImage(ImageDto imageDto) {
        ImageDto imageDto1 = ImageDto.builder()
                .imageName("none")
                .imageUrl("none")
                .personId(0).build();

        return new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                "image.create.ok",
                imageDto1);
    }

    @Override
    public StandardResponse<ImageDto> editImage(ImageDto imageDto) {
        return null;
    }

    @Override
    public StandardResponse<String> deleteImage(String imageId) {
        return null;
    }

    @Override
    public StandardResponse<ImageDto> findByPersonId(Integer personId) {
        return null;
    }

    @Override
    public StandardResponse<List<ImageDto>> findAll() {
        System.out.println("QUIZA LLEGA");
        List<ImageDto> imageDtoList = new ArrayList<>();
        imageDtoList.add(ImageDto.builder().imageName("none").imageUrl("none").personId(0).id("none").build());

        return new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                "image.create.ok",
                imageDtoList);
    }
}
