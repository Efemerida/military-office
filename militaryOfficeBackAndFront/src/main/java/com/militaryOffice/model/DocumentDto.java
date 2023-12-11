package com.militaryOffice.model;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DocumentDto {

    private Integer id;
    private Citizen userId;
    private Employee employeeId;
    private Integer columnName;
    private byte[] file;
    private String fileName;
    private String description;


}
