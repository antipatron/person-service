package com.fakecompany.micro.person.util;

import java.util.Objects;

public class OptionalFieldValidator {

    public static boolean imageComeOnBody(String imageId){
        return !Objects.isNull(imageId);
    }

}
