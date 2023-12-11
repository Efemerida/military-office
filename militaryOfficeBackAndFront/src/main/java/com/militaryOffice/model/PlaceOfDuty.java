package com.militaryOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"placeOfDuty\"")
public class PlaceOfDuty {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "manager", nullable = false, length = 50)
    private String manager;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Column(name = "phone", nullable = false, length = 25)
    private String phone;

    @Column(name = "\"totalSeats\"", nullable = false)
    private Integer totalSeats;

    @Column(name = "\"numberOfAvailableSeats\"", nullable = false)
    private Integer numberOfAvailableSeats;

    @Column(name = "\"typeOfArmy\"", length = 150)
    private String typeOfArmy;

    @JsonIgnore
    @OneToMany(mappedBy = "idPlace")
    private Set<Service> services = new LinkedHashSet<>();

}