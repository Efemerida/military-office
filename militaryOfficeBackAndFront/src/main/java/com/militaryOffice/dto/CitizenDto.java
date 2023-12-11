package com.militaryOffice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CitizenDto {

    private Integer id;

    private String surname;

    private String name;

    private String patronimic;

    private LocalDate dateOfBirth;

    private Boolean gender = false;

    private String passport;


    private String status;

    private String statusCode;

    private String phone;

    private Integer salary;

    private String familyStatus;

    private Integer amountOfChildren;

    private String placeOfBirth;

    private String residenceAddressCurr;

    private String residenceAddress;

    private String index;

    private String levelOfEducation;

    private String speciality;

    private String placeOfCollection;

    private LocalDate expirationDate;

    private String placeOfWork;

    private String jobTitle;

    private String politicalViews;

    private String religiousViews;

    private String driverLicense;

    private Boolean isArchive = false;

    private Integer experience;

    private String militarySpeciality;

    private String sizeHeight;

    private String sizeForm;

    private String sizeShoe;

    private String sizeBelt;

    private String sizeContraindication;

    private String sizeGlove;

    private int militaryOfficeId;

}
