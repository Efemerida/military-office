package com.militaryOffice.controller;

import com.militaryOffice.model.Account;
import com.militaryOffice.services.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class GosUslugiController {

    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/gosuslugi/login")
    public String loginPage(){
        System.out.println(passwordEncoder.encode("123"));
        return "gosUslugi/login";
    }


    @GetMapping("/gosuslugi/registration")
    public String registrationPage(@ModelAttribute("account")Account account){
        return "gosUslugi/registration";
    }

    @PostMapping("/gosuslugi/registration")
    public String registration(@ModelAttribute("account") Account account){
        System.out.println("sdasdasdas");
        authorizationService.register(account);
        return "redirect:/gosuslugi/login";
    }

}
