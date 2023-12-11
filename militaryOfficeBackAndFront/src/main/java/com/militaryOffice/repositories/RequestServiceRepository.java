package com.militaryOffice.repositories;

import com.militaryOffice.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestServiceRepository extends JpaRepository<UserRequest, Integer> {

    List<UserRequest> findAll();

}
