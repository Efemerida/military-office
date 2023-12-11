package com.militaryOffice.controller.summoned;

import com.militaryOffice.model.Parent;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned")
public class ParentController {

    private final ParentService parentService;
    private final CitizenService citizenService;

    @GetMapping("/user/{id}/parents")
    public String getParentPage(@PathVariable("id") int id, Model model){
        List<Parent> parents = parentService.getAllParentByUserId(id);
        model.addAttribute("parents", parents);
        model.addAttribute("id", id);
        model.addAttribute("thereIs", !parents.isEmpty());
        return "summoned/parents/get";
    }
    @RequestMapping(value = "/user/{id}/parents", method = RequestMethod.POST)
    public String editParent(@PathVariable("id") int id, @ModelAttribute Parent tmp, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("parent",tmp);
        return "redirect:/summoned/user/"+id+"/parents/edit";
    }
    @RequestMapping(value = "/user/{id}/parents/delete", method = RequestMethod.POST)
    public String deleteParent(@PathVariable("id") int id, @ModelAttribute Parent tmp){
        parentService.deleteParent(tmp);
        return "redirect:/summoned/user/"+id+"/parents";
    }
    @GetMapping("/user/{id}/parents/edit")
    public String editParent(@PathVariable("id") int id, Model model){
        Parent tmp = (Parent) model.asMap().get("parent");
        Parent parent = parentService.getParentById(tmp.getId());
        model.addAttribute("parent", parent);
        System.out.println(tmp);
        return "summoned/parents/edit";
    }
    @PostMapping("/user/{id}/parents/edit")
    public String editParent(@PathVariable("id") int id, @ModelAttribute("parent")Parent parent){
        parentService.saveWithCitizenId(parent, id);
        return "redirect:/summoned/user/"+id+"/parents";

    }
    @GetMapping("/user/{id}/parents/create")
    public String createParent(@PathVariable("id") int id, Model model){
        Parent parent = new Parent();
        parent.setIdUser(citizenService.getCitizenById(id));
        model.addAttribute("parent", parent);
        model.addAttribute("id", id);
        return "summoned/parents/create";
    }
    @PostMapping("/user/{id}/parents/create")
    public String createParent(@PathVariable("id") int id, @ModelAttribute("parent") Parent parent){
        parentService.saveWithCitizenId(parent, id);
        return "redirect:/summoned/user/"+id+"/parents";
    }

}
