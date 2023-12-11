package com.militaryOffice.controller.summoned;

import com.militaryOffice.model.Inspection;
import com.militaryOffice.model.MedicalCard;
import com.militaryOffice.services.InspectionService;
import com.militaryOffice.services.MedicalCardService;
import com.militaryOffice.services.MilitaryOfficeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned")
public class MedicalController {

    private final MedicalCardService medicalCardService;
    private final InspectionService inspectionService;

    @GetMapping("/user/{id}/medicalCard")
    public String getMedicalCardPage(@PathVariable("id") int id, Model model){
        MedicalCard medicalCard = medicalCardService.getMedicalCardById(id);
        model.addAttribute("card", medicalCard);
        model.addAttribute("thereIs", medicalCard!=null);
        model.addAttribute("id", id);
        return "summoned/medicalCard";
    }

    @GetMapping("/user/{id}/inspection")
    public String getInspectionsPage(@PathVariable("id") int id, Model model){
        List<Inspection> inspections = inspectionService.getAllInspectionByIdUser(id);
        model.addAttribute("inspections", inspections);
        model.addAttribute("thereIs", !inspections.isEmpty());
        model.addAttribute("id", id);
        return "summoned/inspection";
    }

}
