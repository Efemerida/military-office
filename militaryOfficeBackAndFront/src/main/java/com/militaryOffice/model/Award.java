package com.militaryOffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@Table(name = "award")
public class Award {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "\"dateOfReceipt\"", nullable = false)
    private LocalDate dateOfReceipt;

    @Column(name = "reason", nullable = false, length = 350)
    private String reason;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idUser\"", nullable = false)
    private Citizen idUser;

}