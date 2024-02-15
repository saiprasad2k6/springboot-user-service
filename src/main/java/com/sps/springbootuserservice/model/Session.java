package com.sps.springbootuserservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel {
    private String token;
    private Date expiringAt;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus status;

    //S:U
    //1:1
    //M : 1 ==> M:1
    @ManyToOne
    private User user;


}
