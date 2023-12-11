package com.militaryOffice.controller;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.security.AccountDetails;
import com.militaryOffice.services.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AboutMeController {

    private final CitizenService citizenService;



}
