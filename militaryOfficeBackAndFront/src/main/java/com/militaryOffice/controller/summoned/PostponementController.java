package com.militaryOffice.controller.summoned;

import com.militaryOffice.model.Postponement;
import com.militaryOffice.model.PostponementService;
import com.militaryOffice.services.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/summoned")
public class PostponementController {

    private final PostponementService postponementService;
    private final CitizenService citizenService;

    @GetMapping("/user/{id}/postponement")
    public String getAllPostponementPage(@PathVariable("id") int id, Model model){
        List<Postponement> postponementList = postponementService.getAllPostponementByIdUser(id);
        model.addAttribute("postponementList", postponementList);
        model.addAttribute("id",id);
        model.addAttribute("thereIs",!postponementList.isEmpty());
        return "summoned/postponement/get";
    }

    @RequestMapping(value = "/user/{id}/postponement", method = RequestMethod.POST)
    public String editParent(@PathVariable("id") int id, @ModelAttribute Postponement tmp, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("postponement",tmp);
        return "redirect:/summoned/user/"+id+"/postponement/edit";
    }

    @RequestMapping(value = "/user/{id}/postponement/delete", method = RequestMethod.POST)
    public String deleteParent(@PathVariable("id") int id, @ModelAttribute Postponement tmp){
        postponementService.delete(tmp);
        return "redirect:/summoned/user/"+id+"/postponement";
    }


    @GetMapping("/user/{id}/postponement/edit")
    public String editParent(@PathVariable("id") int id, Model model){
        Postponement tmp = (Postponement) model.asMap().get("postponement");
        Postponement postponement = postponementService.getPostponementGetById(tmp.getId());
        model.addAttribute("postponement", postponement);
        return "summoned/postponement/edit";
    }

    @PostMapping("/user/{id}/postponement/edit")
    public String editParent(@PathVariable("id") int id, @ModelAttribute("postponement") Postponement postponement, Model model){
        postponementService.saveWithUserId(postponement,id);
        return "redirect:/summoned/user/"+id+"/postponement";
    }


    @GetMapping("/user/{id}/postponement/create")
    public String createParent(@PathVariable("id") int id, Model model){
        Postponement postponement = new Postponement();
        model.addAttribute("postponement", postponement);
        model.addAttribute("id", id);
        return "summoned/postponement/create";
    }

    @PostMapping("/user/{id}/postponement/create")
    public String createParent(@PathVariable("id") int id, @ModelAttribute("postponement") Postponement postponement, Model model){
        postponementService.saveWithUserId(postponement, id);
        return "redirect:/summoned/user/"+id+"/postponement";
    }

}
