package com.militaryOffice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.PlaceOfDuty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceDto {

    private Integer id;

    private int placeId;

    private String form;

    private Integer time;

    private String militaryUnit;

    private String rota;

    private String platoon;

    private String branch;

    private LocalDate date;

    private Citizen idUser;

    private String jobTitle;

}
