package com.fakecompany.micro.person.adapter.client;


import com.fakecompany.micro.person.config.FeignClientConfiguration;
import com.fakecompany.micro.person.dto.ImageDto;
import com.fakecompany.micro.person.util.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "image-service", url = "${microverse.image-service.url}", configuration = FeignClientConfiguration.class)
public interface ImageClient {

    @RequestMapping(value = "" , consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    StandardResponse<ImageDto> createImage(@RequestBody ImageDto imageDto);

    @RequestMapping(value = "" , consumes = APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    StandardResponse<ImageDto> editImage(@RequestBody ImageDto imageDto);

    @RequestMapping(value = "/delete" , consumes = APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    StandardResponse<String> deleteImage(@RequestParam String imageId);

    @RequestMapping(value = "/get-by-id" , consumes = APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    StandardResponse<ImageDto> findByPersonId(@RequestParam Integer personId);

    @RequestMapping(value = "/get-all" , consumes = APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    StandardResponse<List<ImageDto>> findAll();



}
