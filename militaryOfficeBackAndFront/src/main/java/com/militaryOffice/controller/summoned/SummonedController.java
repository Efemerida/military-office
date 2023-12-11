package com.militaryOffice.controller.summoned;

import com.militaryOffice.dto.CitizenDto;
import com.militaryOffice.mappers.CitizenMapper;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Employee;
import com.militaryOffice.model.FamilyStatus;
import com.militaryOffice.model.MilitaryStatus;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.EmployeeService;
import com.militaryOffice.services.MilitaryOfficeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned")
public class SummonedController {

    private final EmployeeService employeeService;
    private final CitizenService citizenService;
    private final MilitaryOfficeService militaryOfficeService;
    private final CitizenMapper citizenMapper;

    @GetMapping("/aboutMe")
    public String getAboutMePage(Model model){
        Employee employee = employeeService.getEmployeeByAuthentication();
        model.addAttribute("employee", employee);
        return "summoned/aboutMe";
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model){
        List<Citizen> citizens = citizenService.getAllCitizen();
        model.addAttribute("users", citizens);
        return "summoned/users";
    }

    @GetMapping("/user/{id}")
    public String getUserDetailPage(@PathVariable("id") int id, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        model.addAttribute("user", citizen);
        return "summoned/user/user";
    }

    @GetMapping("/user/{id}/edit")
    public String editUserPage(@PathVariable("id") int id, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        CitizenDto citizenDto = citizenMapper.mapToCitizenDto(citizen);
        model.addAttribute("baseUser", citizen);
        model.addAttribute("user", citizenDto);
        model.addAttribute("offices", militaryOfficeService.getAllMilitaryOffices());
        return "summoned/user/edit";
    }

    @PostMapping("/user/{id}/edit")
    public String submitEditUser(@PathVariable("id") int id, @ModelAttribute("user") CitizenDto user){
        System.out.println(user);
        Citizen citizen = citizenMapper.mapToCitizen(user);
        citizenService.save(citizen);
        return "redirect:/summoned/user/" + id;
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") int id){
        citizenService.deleteUser(id);
        return "redirect:/summoned/user/" + id;
    }
    @GetMapping("/user/{id}/restore")
    public String restoreUser(@PathVariable("id") int id){
        citizenService.restoreUser(id);
        return "redirect:/summoned/user/" + id;
    }

    @GetMapping("/user/create")
    public String createUserPage(Model model){
        model.addAttribute("user", new CitizenDto());
        model.addAttribute("statusList", MilitaryStatus.values());
        model.addAttribute("familyStatusList", FamilyStatus.values());
        model.addAttribute("offices", militaryOfficeService.getAllMilitaryOffices());
        return "summoned/user/create";
    }

    @PostMapping("/user/create")
    public String submitCreateUserPage(@ModelAttribute("user") CitizenDto user){
        Citizen citizen = citizenMapper.mapToCitizen(user);
        citizenService.save(citizen);
        return "redirect:/summoned/users";
    }

}
