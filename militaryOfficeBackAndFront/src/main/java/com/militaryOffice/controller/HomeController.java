package com.militaryOffice.controller;

import com.militaryOffice.mappers.CitizenMapper;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Role;
import com.militaryOffice.repositories.CitizenRepository;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Controller
public class HomeController {

    private final SecurityService securityService;

    @GetMapping("/home")
    public String login(){
        return "login";
    }
    @PostMapping("/home")
    public String goToGosUslugi(){
        return "redirect:/gosuslugi/login";
    }

    @GetMapping("/home/success")
    public String successPage(){
        Role role = Role.valueOf(securityService.getRole());
        if(role.equals(Role.ROLE_USER)) return "redirect:/user/aboutMe";
        if(role.equals(Role.ROLE_ADMIN)) return "redirect:/summoned/aboutMe";
        return "redirect:/doctor/aboutMe";
    }
}
