package com.militaryOffice.model;

import com.militaryOffice.repositories.PostponementRepository;
import com.militaryOffice.services.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostponementService {

    private final PostponementRepository postponementRepository;
    private final CitizenService citizenService;

    public List<Postponement> getAllPostponement(){
        Citizen citizen = citizenService.getCitizenByAuthentication();
        List<Postponement> postponements = postponementRepository.findAllByidUser(citizen);
        return postponements;
    }


    public List<Postponement> getAllPostponementByIdUser(int id){
        Citizen citizen = citizenService.getCitizenById(id);
        List<Postponement> postponements = postponementRepository.findAllByidUser(citizen);
        return postponements;
    }

    public void delete(Postponement postponement){
        postponementRepository.delete(postponement);
    }

    public Postponement getPostponementGetById(int id){
        return postponementRepository.findById(id);
    }

    public void saveWithUserId(Postponement postponement, int id){
        postponement.setIdUser(citizenService.getCitizenById(id));
        postponementRepository.save(postponement);
    }
}
