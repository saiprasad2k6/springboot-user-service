package com.sps.springbootuserservice.model;

import jakarta.persistence.*;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
