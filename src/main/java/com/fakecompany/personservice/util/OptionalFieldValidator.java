package com.fakecompany.personservice.util;

import java.util.Objects;

public class OptionalFieldValidator {

    public static boolean imageComeOnBody(String imageId){
        return !Objects.isNull(imageId);
    }

}
