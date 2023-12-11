package com.militaryOffice.controller.doctor;

import com.militaryOffice.model.MedicalCard;
import com.militaryOffice.services.EmployeeService;
import com.militaryOffice.services.MedicalCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/doctor")
public class MedicalCardController {

    private final MedicalCardService medicalCardService;
    private final EmployeeService employeeService;

    @GetMapping("/user/{id}/medicalCard")
    public String getMedicalCardPage(@PathVariable("id") int id, Model model){
        MedicalCard medicalCard = medicalCardService.getMedicalCardById(id);
        model.addAttribute("card", medicalCard);
        model.addAttribute("thereIs", medicalCard!=null);
        model.addAttribute("mainDoctor", employeeService.isMainDoctor());
        return "doctor/medicalCard/get";
    }

    @GetMapping("/user/{id}/medicalCard/edit")
    public String getMedicalCardEditPage(@PathVariable("id") int id, Model model){
        MedicalCard medicalCard = medicalCardService.getMedicalCardById(id);
        model.addAttribute("card", medicalCard);
        return "doctor/medicalCard/edit";
    }

    @PostMapping("/user/{id}/medicalCard/edit")
    public String postMedicalCardEditPage(@PathVariable("id") int id,@ModelAttribute("card") MedicalCard card){
        MedicalCard medicalCard = medicalCardService.getMedicalCardById(id);
        card.setId(medicalCard.getId());
        medicalCardService.saveWithIdUser(card, id);
        return "redirect:/doctor/user/"+id+"/medicalCard";
    }

    @GetMapping("/user/{id}/medicalCard/create")
    public String getMedicalCardCreatePage(@PathVariable("id") int id, Model model){
        MedicalCard medicalCard = new MedicalCard();
        model.addAttribute("card", medicalCard);
        return "doctor/medicalCard/create";
    }

    @PostMapping("/user/{id}/medicalCard/create")
    public String postMedicalCardCreatePage(@PathVariable("id") int id,@ModelAttribute("card") MedicalCard card){
        medicalCardService.saveWithIdUser(card, id);
        return "redirect:/doctor/user/"+id+"/medicalCard";
    }
}
