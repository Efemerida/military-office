package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends CrudRepository<Citizen, Integer> {
    Citizen findByPassport(String passport);
    List<Citizen> findAll();
}
