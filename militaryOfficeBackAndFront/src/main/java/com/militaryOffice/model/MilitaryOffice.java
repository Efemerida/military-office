package com.militaryOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "militaryOffice")
public class MilitaryOffice {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "manager", nullable = false, length = 50)
    private String manager;

    @Column(name = "adress", nullable = false, length = 150)
    private String adress;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "militaryOffice")
    private Set<Citizen> citizens = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "militaryOffice")
    private Set<Subpoena> subpoenas = new LinkedHashSet<>();

}