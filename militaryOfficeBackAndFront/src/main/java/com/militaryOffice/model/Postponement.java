package com.militaryOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "postponement")
public class Postponement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "\"dateOfAcceptance\"", nullable = false)
    private LocalDate dateOfAcceptance;

    @Column(name = "\"endDate\"", nullable = false)
    private LocalDate endDate;

    @Column(name = "reason", nullable = false, length = 300)
    private String reason;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idUser\"", nullable = false)
    private Citizen idUser;

}