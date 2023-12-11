package com.militaryOffice.repositories;

import com.militaryOffice.model.Award;
import com.militaryOffice.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {

    List<Award> findAllByidUser(Citizen idUser);

}
