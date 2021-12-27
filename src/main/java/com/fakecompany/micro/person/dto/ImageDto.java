package com.fakecompany.micro.person.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ImageDto {

    private String id;
    private String image;
    @NotNull
    private Integer personId;
}
