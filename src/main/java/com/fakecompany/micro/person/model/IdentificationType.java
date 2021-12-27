package com.fakecompany.micro.person.model;



import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "identification_type")
@Data
public class IdentificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
}
