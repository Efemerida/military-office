package com.militaryOffice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.MilitaryOffice;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SubpoenaDto {

    private int id;

    private int userId;

    private LocalDate dateOfDelivery;

    private LocalDate dateOfAttendance;

    private String reason;


    private String documents;

    private int militaryOfficeId;

}
