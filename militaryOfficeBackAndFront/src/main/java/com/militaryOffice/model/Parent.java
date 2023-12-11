package com.militaryOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "parent")
@ToString
public class Parent {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "patronimic", length = 50)
    private String patronimic;

    @Column(name = "affiliation", nullable = false, length = 50)
    private String affiliation;

    @Column(name = "\"dateOfBirth\"", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "\"placeOfWork\"", length = 150)
    private String placeOfWork;

    @Column(name = "\"jobTitle\"", length = 150)
    private String jobTitle;

    @Column(name = "\"residenceAddressCurr\"", nullable = false, length = 150)
    private String residenceAddressCurr;

    @Column(name = "\"residenceAddress\"", nullable = false, length = 150)
    private String residenceAddress;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idUser\"")
    private Citizen idUser;

}