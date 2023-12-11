package com.militaryOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "service")
public class Service {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idPlace\"", nullable = false)
    private PlaceOfDuty idPlace;

    @Column(name = "form", nullable = false, length = 50)
    private String form;

    @Column(name = "\"time\"", nullable = false)
    private Integer time;

    @Column(name = "\"militaryUnit\"", length = 10)
    private String militaryUnit;

    @Column(name = "rota", length = 10)
    private String rota;

    @Column(name = "platoon", length = 10)
    private String platoon;

    @Column(name = "branch", length = 10)
    private String branch;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idUser\"", nullable = false)
    private Citizen idUser;

    @Column(name = "\"jobTitle\"", length = 150)
    private String jobTitle;

}