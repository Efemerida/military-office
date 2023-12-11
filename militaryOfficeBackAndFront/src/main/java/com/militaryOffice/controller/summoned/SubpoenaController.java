package com.militaryOffice.controller.summoned;

import com.militaryOffice.dto.SubpoenaDto;
import com.militaryOffice.dto.SubpoenaMapper;
import com.militaryOffice.model.MilitaryOffice;
import com.militaryOffice.model.Subpoena;
import com.militaryOffice.services.MilitaryOfficeService;
import com.militaryOffice.services.SubpoenaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned")
public class SubpoenaController {

    private final SubpoenaService subpoenaService;
    private final MilitaryOfficeService militaryOfficeService;
    private final SubpoenaMapper subpoenaMapper;

    @GetMapping("/user/{id}/subpoena")
    public String getAllSubpoenaPage(@PathVariable("id") int id, Model model){
        List<Subpoena> subpoenaList = subpoenaService.getAllSubpoenaByIdUser(id);
        model.addAttribute("subpoenaList", subpoenaList);
        System.out.println(subpoenaList);
        model.addAttribute("id",id);
        model.addAttribute("thereIs", !subpoenaList.isEmpty());
        return "summoned/subpoena/get";
    }

    @RequestMapping(value = "/user/{id}/subpoena", method = RequestMethod.POST)
    public String editParent(@PathVariable("id") int id, @ModelAttribute SubpoenaDto tmp, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("subpoena",tmp);
        return "redirect:/summoned/user/"+id+"/subpoena/edit";
    }

    @RequestMapping(value = "/user/{id}/subpoena/delete", method = RequestMethod.POST)
    public String deleteParent(@PathVariable("id") int id, @ModelAttribute("tmp") SubpoenaDto tmp){
        subpoenaService.deleteSubpoenaDto(tmp);
        return "redirect:/summoned/user/"+id+"/subpoena";
    }


    @GetMapping("/user/{id}/subpoena/edit")
    public String editParent(@PathVariable("id") int id, Model model){
        SubpoenaDto tmp = (SubpoenaDto) model.asMap().get("subpoena");
        Subpoena subpoena = subpoenaService.getSubpoenaById(tmp.getId());
        SubpoenaDto subpoenaDto = subpoenaMapper.mapToSubpoenaDto(subpoena);
        List<MilitaryOffice> officeList = militaryOfficeService.getAllMilitaryOffices();
        model.addAttribute("subpoenaOld", subpoena);
        model.addAttribute("subpoena", subpoenaDto);
        model.addAttribute("id",id);
        model.addAttribute("offices", officeList);
        return "summoned/subpoena/edit";
    }

    @PostMapping("/user/{id}/subpoena/edit")
    public String editParent(@PathVariable("id") int id, @ModelAttribute("subpoena") SubpoenaDto subpoena){
        subpoena.setUserId(id);
        Subpoena subpoenas = subpoenaMapper.mapToSubpoena(subpoena);
        subpoenaService.saveWithUserId(subpoenas, id);
        return "redirect:/summoned/user/"+id+"/subpoena";

    }


    @GetMapping("/user/{id}/subpoena/create")
    public String createParent(@PathVariable("id") int id, Model model){
        List<MilitaryOffice> officeList =militaryOfficeService.getAllMilitaryOffices();
        SubpoenaDto subpoena = new SubpoenaDto();
        model.addAttribute("subpoena", subpoena);
        model.addAttribute("offices", officeList);
        model.addAttribute("id", id);
        return "summoned/subpoena/create";
    }

    @PostMapping("/user/{id}/subpoena/create")
    public String createParent(@PathVariable("id") int id, @ModelAttribute("subpoena") SubpoenaDto subpoena, Model model){
        subpoena.setUserId(id);
        Subpoena subpoena1 = subpoenaMapper.mapToSubpoena(subpoena);
        subpoenaService.saveWithUserId(subpoena1,id);
        return "redirect:/summoned/user/"+id+"/subpoena";
    }

}
