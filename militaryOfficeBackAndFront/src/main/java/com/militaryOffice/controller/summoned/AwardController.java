package com.militaryOffice.controller.summoned;

import com.militaryOffice.model.Award;
import com.militaryOffice.model.Citizen;
import com.militaryOffice.services.AwardService;
import com.militaryOffice.services.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned")
public class AwardController {

    private final CitizenService citizenService;
    private final AwardService awardService;
    @GetMapping("/user/{id}/awards")
    public String awards(@PathVariable("id") int id, Model model){
        List<Award> awards = awardService.getAllAwardsById(id);
        model.addAttribute("awards", awards);
        model.addAttribute("id", id);
        model.addAttribute("thereIs", !awards.isEmpty());
        return "summoned/awards/get";
    }
    @RequestMapping(value = "/user/{id}/awards", method = RequestMethod.POST)
    public String editAward(@PathVariable("id") int id, @ModelAttribute Award tmp, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("award",tmp);
        System.out.println("\ngettttt " + tmp + "\n");
        return "redirect:/summoned/user/"+id+"/awards/edit";
    }


    @RequestMapping(value = "/user/{id}/awards/delete", method = RequestMethod.POST)
    public String deleteAward(@PathVariable("id") int id, @ModelAttribute Award tmp){
        System.out.println(tmp);
        awardService.delete(tmp);
        return "redirect:/summoned/user/"+id+"/awards";
    }

    @GetMapping("/user/{id}/awards/edit")
    public String editAward(@PathVariable("id") int id, Model model){
        Award tmp = (Award) model.asMap().get("award");
        Award award = awardService.findById(tmp.getId());
        model.addAttribute("award", award);
        model.addAttribute("id",id);
        System.out.println("edit start is " + tmp);
        return "summoned/awards/edit";
    }

    @PostMapping("/user/{id}/awards/edit")
    public String editAward(@PathVariable("id") int id, @ModelAttribute("award") Award award, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        award.setIdUser(citizen);
        awardService.save(award);
        System.out.println("edit is " + award);
        return "redirect:/summoned/user/"+id+"/awards";
    }

    @GetMapping("/user/{id}/awards/create")
    public String createAward(@PathVariable("id") int id, Model model){
        Award award = new Award();
        model.addAttribute("award", award);
        model.addAttribute("id", id);
        return "summoned/awards/create";
    }

    @PostMapping("/user/{id}/awards/create")
    public String createAward(@PathVariable("id") int id, @ModelAttribute("award")Award award, Model model){
        Citizen citizen = citizenService.getCitizenById(id);
        award.setIdUser(citizen);
        awardService.save(award);
        return "redirect:/summoned/user/"+id+"/awards";
    }

}
