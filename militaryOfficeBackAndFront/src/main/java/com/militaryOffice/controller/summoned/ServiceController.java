package com.militaryOffice.controller.summoned;

import com.militaryOffice.dto.ServiceDto;
import com.militaryOffice.mappers.ServiceMapper;
import com.militaryOffice.model.MilitaryStatus;
import com.militaryOffice.model.PlaceOfDuty;
import com.militaryOffice.model.Service;
import com.militaryOffice.model.ServiceStatus;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.PlaceOfDutyService;
import com.militaryOffice.services.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/summoned")
public class ServiceController {

    private final ServiceService serviceService;
    private final CitizenService citizenService;
    private final PlaceOfDutyService placeOfDutyService;
    private final ServiceMapper serviceMapper;

    @GetMapping(("/user/{id}/service"))
    public String getAllServicesPage(@PathVariable("id") int id, Model model){
        List<Service> services = serviceService.getAllServicesById(id);
        model.addAttribute("services",services);
        model.addAttribute("id",id);
        model.addAttribute("thereIs",!services.isEmpty());
        return "summoned/service/get";
    }


    @GetMapping("/user/{id}/service/create")
    public String createService(@PathVariable("id") int id, Model model){
        model.addAttribute("service", new ServiceDto());
        List<PlaceOfDuty> placeOfDutyList = placeOfDutyService.getAllPlaceOfDuty();
        model.addAttribute("forms", ServiceStatus.values());
        model.addAttribute("places",placeOfDutyList);
        model.addAttribute("id", id);
        return "summoned/service/create";
    }


    @PostMapping("/user/{id}/service/create")
    public String createParent(@PathVariable("id") int id, @ModelAttribute("service") ServiceDto service, Model model){
        serviceService.saveServiceDtoWithIdUSer(service, id);
        return "redirect:/summoned/user/"+id+"/service";
    }


    @RequestMapping(value = "/user/{id}/service", method = RequestMethod.POST)
    public String editAward(@PathVariable("id") int id, @ModelAttribute ServiceDto service, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("service",service);
        return "redirect:/summoned/user/"+id+"/service/edit";
    }

    @RequestMapping(value = "/user/{id}/service/delete", method = RequestMethod.POST)
    public String deleteService(@PathVariable("id") int id, ServiceDto service){
        serviceService.deleteServiceDto(service);
        return "redirect:/summoned/user/"+id+"/service";
    }

    @GetMapping("/user/{id}/service/edit")
    public String editService(@PathVariable("id") int id, Model model){

        ServiceDto tmp = (ServiceDto) model.asMap().get("service");
        System.out.println("service getEdit is " + tmp);
        Service service = serviceService.getServiceById(tmp.getId());
        ServiceDto oldService = serviceMapper.mapToServiceDto(service);
        List<PlaceOfDuty> placeOfDutyList = placeOfDutyService.getAllPlaceOfDuty();

        model.addAttribute("places",placeOfDutyList);
        model.addAttribute("oldService", oldService);
        model.addAttribute("forms", ServiceStatus.values());
        model.addAttribute("id",id);
        return "summoned/service/edit";
    }

    @PostMapping("/user/{id}/service/edit")
    public String editService(@PathVariable("id") int id, @ModelAttribute("oldService") ServiceDto oldService){
        serviceService.saveServiceDtoWithIdUSer(oldService, id);
        return "redirect:/summoned/user/"+id+"/service";

    }
}
