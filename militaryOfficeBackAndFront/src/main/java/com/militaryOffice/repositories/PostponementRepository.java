package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Postponement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostponementRepository extends JpaRepository<Postponement, Integer> {

    List<Postponement> findAllByidUser(Citizen idUser);
    Postponement findById(int id);

}
