package com.militaryOffice.controller.user;

import com.militaryOffice.model.MilitaryOffice;
import com.militaryOffice.model.UserRequest;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.MilitaryOfficeService;
import com.militaryOffice.services.NotificationService;
import com.militaryOffice.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user/other")
public class OtherController {

    private final NotificationService notificationService;
    private final RequestService requestService;
    private final MilitaryOfficeService militaryOfficeService;
    private final CitizenService citizenService;

    @GetMapping("")
    public String getOtherPage(Model model){
        model.addAttribute("count", notificationService.getCountNewNotification());
        return "user/other";
    }

    @GetMapping("/changeMilitaryOffice")
    public String getFrom(Model model){

        if(!requestService.isActiveChange()) {

            List<MilitaryOffice> offices = militaryOfficeService.getOfficesWithoutCurrent();

            model.addAttribute("yes" ,true);
            model.addAttribute("offices", offices);
            model.addAttribute("request", new UserRequest());
            model.addAttribute("currOffice", citizenService.getCitizenByAuthentication().getMilitaryOffice());
            return "user/changeOffice";
        }
        model.addAttribute("yes" ,false);
        return "user/changeOffice";
    }

    @PostMapping("/changeMilitaryOffice")
    public String getForm(@ModelAttribute("request") UserRequest request, Model model){
        requestService.changeOffice(request);
        return "redirect:/user/other";
    }

    @GetMapping("/getCertificate")
    public String getSuccessfully(Model model){
        model.addAttribute("count",notificationService.getCountNewNotification());
        System.out.println("GEEEEETtT");
        if(!requestService.isActiveCertificate()){
            model.addAttribute("currOffice", citizenService.getCitizenByAuthentication().getMilitaryOffice());
            model.addAttribute("yes", true);
            model.addAttribute("request", new UserRequest());
            return "user/getCertificate";
        }
        model.addAttribute("yes", false);
        return "user/getCertificate";
    }

    @PostMapping("/getCertificate")
    public String getSuccessfully(@ModelAttribute("request") UserRequest requests, Model model){
        requestService.getCertificate(requests);
        return "redirect:/user/other";
    }

}
