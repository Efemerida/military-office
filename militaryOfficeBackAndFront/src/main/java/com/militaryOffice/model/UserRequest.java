package com.militaryOffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_request")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Citizen idUser;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeRequest type;

    @Column(name = "data", length = 300)
    private String data;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserRequestStatus status;

}