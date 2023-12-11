package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.PlaceOfDuty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceOfDutyRepository extends JpaRepository<PlaceOfDuty, Integer> {
    PlaceOfDuty findById(int id);
    List<PlaceOfDuty> findAll();

}
