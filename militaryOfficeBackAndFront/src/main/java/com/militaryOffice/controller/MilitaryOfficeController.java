package com.militaryOffice.controller;

import com.militaryOffice.model.MilitaryOffice;
import com.militaryOffice.repositories.MilitaryOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
public class MilitaryOfficeController {

    MilitaryOfficeRepository militaryOfficeRepository;

    @PostMapping("/add")
    public void addMilitaryOffice(@RequestBody MilitaryOffice militaryOffice){
        System.out.println(militaryOffice);
        militaryOfficeRepository.save(militaryOffice);
    }
    @GetMapping("/getOffices")
    public ResponseEntity<?> addMilitaryOffice(){
        List<MilitaryOffice> offices = militaryOfficeRepository.findAll();
        System.out.println(offices);
        return ResponseEntity.ok(offices);
    }

}
