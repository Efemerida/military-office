package com.militaryOffice.controller.doctor;

import com.militaryOffice.model.Inspection;
import com.militaryOffice.services.EmployeeService;
import com.militaryOffice.services.InspectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/doctor/user")
public class InspectionController {

    private final InspectionService inspectionService;
    private final EmployeeService employeeService;

    @GetMapping("/{id}/inspection")
    public String getAllInspectionPage(@PathVariable("id") int id, Model model){
        List<Inspection> inspectionList = inspectionService.getAllInspectionByIdUser(id);
        model.addAttribute("inspections", inspectionList);
        model.addAttribute("id",id);
        model.addAttribute("isMainDoctor", employeeService.isMainDoctor());
        model.addAttribute("thereIs", !inspectionList.isEmpty());
        return "doctor/inspection/get";
    }

    @GetMapping("/{id}/inspection/create")
    public String createInspection(@PathVariable("id") int id, Model model){
        Inspection inspection = new Inspection();
        model.addAttribute("inspection", inspection);
        model.addAttribute("isMainDoctor", true);
        model.addAttribute("id", id);
        return "doctor/inspection/create";

    }


    @PostMapping("/{id}/inspection/create")
    public String createInspection(@ModelAttribute("inspection") Inspection inspection, @PathVariable("id") int id, Model model){;
        inspectionService.saveWithIdUser(inspection, id);
        return "redirect:/doctor/user/"+id+"/inspection";
    }


}
