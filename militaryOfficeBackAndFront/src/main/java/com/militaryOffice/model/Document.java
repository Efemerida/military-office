package com.militaryOffice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "document")
@ToString
public class Document {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Citizen user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "column_name")
    private Integer columnName;

    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_name", nullable = false, length = 100)
    private String fileName;

    @Column(name = "description", length = 400)
    private String description;

}