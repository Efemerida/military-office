package com.militaryOffice.services;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Inspection;
import com.militaryOffice.repositories.InspectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final CitizenService citizenService;
    private final EmployeeService employeeService;

    public List<Inspection> getAllInspection(){
        Citizen citizen = citizenService.getCitizenByAuthentication();
        List<Inspection> inspections = inspectionRepository.findAllByidUser(citizen);
        return inspections;
    }

    public List<Inspection> getAllInspectionByIdUser(int id){
        Citizen citizen = citizenService.getCitizenById(id);
        List<Inspection> inspections = inspectionRepository.findAllByidUser(citizen);
        return inspections;
    }

    public void saveWithIdUser(Inspection inspection, int id) {
        inspection.setIdUser(citizenService.getCitizenById(id));
        inspection.setDoctor(employeeService.getEmployeeByAuthentication());
        inspection.setId(0);
        inspectionRepository.save(inspection);
    }
}
