package com.militaryOffice.services;

import com.militaryOffice.model.PlaceOfDuty;
import com.militaryOffice.repositories.PlaceOfDutyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceOfDutyService {

    private final PlaceOfDutyRepository placeOfDutyRepository;


    public PlaceOfDuty getPlaceOfDutyById(int id){
        return placeOfDutyRepository.findById(id);
    }

    public List<PlaceOfDuty> getAllPlaceOfDuty(){
        return placeOfDutyRepository.findAll();
    }
}
