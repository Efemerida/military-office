package com.militaryOffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"medicalCard\"")
public class MedicalCard {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idUser\"", nullable = false)
    private Citizen idUser;

    @Column(name = "\"chronicDiseases\"", nullable = false, length = 150)
    private String chronicDiseases;

    @Column(name = "\"mentalDisorders\"", nullable = false, length = 150)
    private String mentalDisorders;

    @Column(name = "diet", nullable = false, length = 150)
    private String diet;

    @Column(name = "disability", nullable = false, length = 150)
    private String disability;

    @Column(name = "\"physicalDevelopment\"", nullable = false, length = 50)
    private String physicalDevelopment;

    @Column(name = "\"mentalDevelopment\"", nullable = false, length = 50)
    private String mentalDevelopment;

    @Column(name = "category", nullable = false, length = 10)
    private String category;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Integer weight;

}