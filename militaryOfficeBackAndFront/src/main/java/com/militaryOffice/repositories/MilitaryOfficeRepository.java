package com.militaryOffice.repositories;

import com.militaryOffice.model.MilitaryOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilitaryOfficeRepository extends JpaRepository<MilitaryOffice, Integer> {

    List<MilitaryOffice> findAll();

}
