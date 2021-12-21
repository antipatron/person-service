package com.fakecompany.micro.person.adapters.image;


import com.fakecompany.micro.person.config.FeignClientConfiguration;
import com.fakecompany.micro.person.dto.ImageDto;
import com.fakecompany.micro.person.util.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@FeignClient(name = "image-service", url = "${microverse.image-service.url}", configuration = FeignClientConfiguration.class)
public interface ImageClient {

    @RequestMapping(value = "" , consumes = MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    StandardResponse<ImageDto> createImage(@RequestPart("image") MultipartFile image, @RequestParam("personId") Integer personId);


}
