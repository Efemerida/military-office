package com.militaryOffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "inspection")
public class Inspection {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Citizen idUser;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "conclusion", nullable = false, length = 400)
    private String conclusion;

    @Column(name = "category", nullable = false, length = 10)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor")
    private Employee doctor;

}