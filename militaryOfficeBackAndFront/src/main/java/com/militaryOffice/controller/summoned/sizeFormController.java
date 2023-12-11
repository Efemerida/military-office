package com.militaryOffice.controller.summoned;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.services.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned/user")
public class sizeFormController {

    private final CitizenService citizenService;

    @GetMapping("/{id}/sizeForm")
    public String getSizeFormPage(@PathVariable("id") int id, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        model.addAttribute("user", citizen);
        model.addAttribute("thereIs", citizen.getSizeForm()!=null);
        model.addAttribute("id", id);
        return "summoned/sizeForm/get";
    }

    @GetMapping("/{id}/sizeForm/edit")
    public String getChangeSizeFormPage(@PathVariable("id") int id, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        model.addAttribute("user", citizen);
        model.addAttribute("thereIs", citizen.getSizeForm()!=null);
        model.addAttribute("id", id);
        return "summoned/sizeForm/edit";
    }

    @PostMapping("/{id}/sizeForm/edit")
    public String postChangeSizeFormPage(@PathVariable("id") int id, @ModelAttribute("user") Citizen user){
        citizenService.saveSizeForm(user, id);
        return "redirect:/summoned/user/"+id+"/sizeForm";
    }

}
