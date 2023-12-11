package com.militaryOffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false, length = 150)
    private String surname;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "patronimic", length = 150)
    private String patronimic;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Column(name = "passport", nullable = false, length = 10)
    private String passport;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "level_of_education", nullable = false, length = 300)
    private String levelOfEducation;

    @Column(name = "speciality", length = 300)
    private String speciality;

    @Column(name = "place_of_collection", length = 300)
    private String placeOfCollection;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "job_title", length = 300)
    private String jobTitle;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "department", length = 200)
    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany(mappedBy = "doctor")
    private Set<Inspection> inspections = new LinkedHashSet<>();

}