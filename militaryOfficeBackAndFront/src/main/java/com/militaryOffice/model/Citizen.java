package com.militaryOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "citizen")
public class Citizen {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "patronimic", length = 150)
    private String patronimic;

    @Column(name = "\"dateOfBirth\"", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Column(name = "passport", nullable = false, length = 10)
    private String passport;

    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MilitaryStatus status;

    @Column(name = "\"statusCode\"", nullable = false, length = 50)
    private String statusCode;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "\"familyStatus\"", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;

    @Column(name = "\"amountOfChildren\"", nullable = false)
    private Integer amountOfChildren;

    @Column(name = "\"placeOfBirth\"", nullable = false, length = 150)
    private String placeOfBirth;

    @Column(name = "\"residenceAddressCurr\"", nullable = false, length = 150)
    private String residenceAddressCurr;

    @Column(name = "\"residenceAddress\"", nullable = false, length = 150)
    private String residenceAddress;

    @Column(name = "index", nullable = false, length = 10)
    private String index;

    @Column(name = "\"levelOfEducation\"", nullable = false, length = 150)
    private String levelOfEducation;

    @Column(name = "speciality", length = 150)
    private String speciality;

    @Column(name = "\"placeOfCollection\"", length = 150)
    private String placeOfCollection;

    @Column(name = "\"expirationDate\"")
    private LocalDate expirationDate;

    @Column(name = "\"placeOfWork\"", length = 150)
    private String placeOfWork;

    @Column(name = "\"jobTitle\"", length = 150)
    private String jobTitle;

    @Column(name = "\"politicalViews\"", nullable = false, length = 150)
    private String politicalViews;

    @Column(name = "\"religiousViews\"", nullable = false, length = 150)
    private String religiousViews;

    @Column(name = "\"driverLicense\"", length = 150)
    private String driverLicense;

    @Column(name = "\"isArchive\"", nullable = false)
    private Boolean isArchive = false;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "\"militarySpeciality\"", length = 150)
    private String militarySpeciality;

    @Column(name = "\"sizeHeight\"", length = 10)
    private String sizeHeight;

    @Column(name = "\"sizeForm\"", length = 10)
    private String sizeForm;

    @Column(name = "\"sizeShoe\"", length = 10)
    private String sizeShoe;

    @Column(name = "\"sizeBelt\"", length = 10)
    private String sizeBelt;

    @Column(name = "\"sizeContraindication\"", length = 10)
    private String sizeContraindication;

    @Column(name = "\"sizeGlove\"", length = 10)
    private String sizeGlove;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "militaryOffice", referencedColumnName = "id")
    private MilitaryOffice militaryOffice;

    @OneToMany(mappedBy = "idUser")
    private Set<Award> awards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Inspection> inspections = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<MedicalCard> medicalCards = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Parent> parents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Postponement> postponements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Service> services = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Subpoena> subpoenas = new LinkedHashSet<>();

}