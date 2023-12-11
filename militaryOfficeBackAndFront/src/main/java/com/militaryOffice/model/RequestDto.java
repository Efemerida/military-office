package com.militaryOffice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {

    private Integer id;

    private int userId;

    private TypeRequest type;

    private String data;

    private UserRequestStatus status;

}
