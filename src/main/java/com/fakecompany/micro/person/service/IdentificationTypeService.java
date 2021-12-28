package com.fakecompany.micro.person.service;


import com.fakecompany.common.exception.DataNotFoundException;
import com.fakecompany.micro.person.model.IdentificationType;
import com.fakecompany.micro.person.model.IdentificationTypeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class IdentificationTypeService {
    private IdentificationTypeRepository identificationTypeRepository;

    public IdentificationTypeService(IdentificationTypeRepository identificationTypeRepository) {
        this.identificationTypeRepository = identificationTypeRepository;
    }

    public IdentificationType findById(Integer id){
        if(Objects.isNull(id)){
            throw new ObjectNotFoundException(id, "exception.objeto_no_encontrado");
        }
        return identificationTypeRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("exception.data_not_found.IdentificationType"));
    }
}
