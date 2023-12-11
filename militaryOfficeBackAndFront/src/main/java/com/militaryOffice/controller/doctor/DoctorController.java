package com.militaryOffice.controller.doctor;

import com.militaryOffice.model.*;
import com.militaryOffice.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final EmployeeService employeeService;
    private final CitizenService citizenService;
    private final AwardService awardService;
    private final ParentService parentService;
    private final PostponementService postponementService;
    private final ServiceService serviceService;
    private final SubpoenaService subpoenaService;

    @GetMapping("/aboutMe")
    public String getAboutMePage(Model model){
        Employee employee = employeeService.getEmployeeByAuthentication();
        model.addAttribute("employee", employee);
        return "doctor/aboutMe";
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model){
        List<Citizen> citizens = citizenService.getAllCitizen();
        model.addAttribute("users", citizens);
        return "doctor/users";
    }

    @GetMapping("/user/{id}/awards")
    public String awards(@PathVariable("id") int id, Model model){
        List<Award> awards = awardService.getAllAwardsById(id);
        model.addAttribute("awards", awards);
        model.addAttribute("thereIs", !awards.isEmpty());
        return "doctor/awards";
    }

    @GetMapping("/user/{id}/parents")
    public String getParentPage(@PathVariable("id") int id, Model model){
        List<Parent> parents = parentService.getAllParentByUserId(id);
        model.addAttribute("parents", parents);
        model.addAttribute("id", id);
        model.addAttribute("thereIs", !parents.isEmpty());
        return "doctor/parents";
    }

    @GetMapping("/user/{id}/postponement")
    public String getAllPostponementPage(@PathVariable("id") int id, Model model){
        List<Postponement> postponementList = postponementService.getAllPostponementByIdUser(id);
        model.addAttribute("postponementList", postponementList);
        model.addAttribute("id",id);
        model.addAttribute("thereIs",!postponementList.isEmpty());
        return "doctor/postponement";
    }

    @GetMapping(("/user/{id}/service"))
    public String getAllServicesPage(@PathVariable("id") int id, Model model){
        List<Service> services = serviceService.getAllServicesById(id);
        model.addAttribute("services",services);
        model.addAttribute("id",id);
        model.addAttribute("thereIs",!services.isEmpty());
        return "doctor/service";
    }

    @GetMapping("/user/{id}/subpoena")
    public String getAllSubpoenaPage(@PathVariable("id") int id, Model model){
        List<Subpoena> subpoenaList = subpoenaService.getAllSubpoenaByIdUser(id);
        model.addAttribute("subpoenaList", subpoenaList);
        System.out.println(subpoenaList);
        model.addAttribute("id",id);
        model.addAttribute("thereIs", !subpoenaList.isEmpty());
        return "doctor/subpoena";
    }

    @GetMapping("/user/{id}/sizeForm")
    public String getSizeFormPage(@PathVariable("id") int id, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        model.addAttribute("citizen", citizen);
        model.addAttribute("thereIs", citizen.getSizeForm()!=null);
        return "doctor/sizeForm";
    }

    @GetMapping("/user/{id}")
    public String getUserDetailPage(@PathVariable("id") int id, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        model.addAttribute("user", citizen);
        return "doctor/user";
    }

}
