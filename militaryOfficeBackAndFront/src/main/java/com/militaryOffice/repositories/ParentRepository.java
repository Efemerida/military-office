package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    List<Parent> findAllByidUser(Citizen idUser);
    Parent findById(int id);
}
