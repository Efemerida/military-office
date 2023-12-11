package com.militaryOffice.controller.summoned;

import com.militaryOffice.mappers.RequestMapper;
import com.militaryOffice.model.*;
import com.militaryOffice.services.CitizenService;
import com.militaryOffice.services.RequestService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/summoned/requests")
public class RequestController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;
    private final CitizenService citizenService;
    @GetMapping("")
    public String getRequests(Model model){
        List<UserRequest> requests = requestService.getAllRequests();
        model.addAttribute("requests",requests);
        model.addAttribute("tmp", new RequestDto());
        return "summoned/userRequests";

    }

    @RequestMapping( value = "/delete", method = RequestMethod.POST)
    public String deleteRequest(@ModelAttribute("tmp") RequestDto tmp){
        requestService.deleteChange(requestMapper.mapToRequest(tmp));
        return "redirect:/summoned/requests";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String acceptRequest(@ModelAttribute("tmp") RequestDto tmp, RedirectAttributes redirectAttributes){
        if(tmp.getType().equals(TypeRequest.CHANGEOFFICE)) {
            requestService.acceptChange(requestMapper.mapToRequest(tmp));
            return "redirect:/summoned/requests";
        }
        redirectAttributes.addFlashAttribute("user", tmp.getUserId());
        return "redirect:/summoned/requests/acceptCertificate";

    }

    @GetMapping("/acceptCertificate")
    public String getAcceptCertificatePage(Model model){
        Document document = new Document();
        Citizen citizen = citizenService.getCitizenById((int) model.asMap().get("user"));
        document.setUser(citizen);
        model.addAttribute("tmp", document);
        model.addAttribute("error", false);
        return "summoned/downloadCertificate";
    }

    @SneakyThrows
    @PostMapping("/acceptCertificate")
    public String uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute("tmp") Document tmp, Model model) {
        System.out.println("AADASDASDADASdA");

        requestService.saveFile(file, tmp);
        return "redirect:/summoned/requests";
    }
}
