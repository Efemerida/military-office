package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalCardRepository extends JpaRepository<MedicalCard,Integer> {
    MedicalCard findByidUser(Citizen idUser);
}
