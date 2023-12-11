package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Subpoena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubpoenaRepository extends JpaRepository<Subpoena, Integer> {

    List<Subpoena> findAllByidUser(Citizen idUser);
    Subpoena findById(int id);

}
