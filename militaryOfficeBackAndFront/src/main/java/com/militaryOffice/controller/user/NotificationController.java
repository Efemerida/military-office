package com.militaryOffice.controller.user;

import com.militaryOffice.model.Notification;
import com.militaryOffice.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notification")
    public String getAllNotificationPage(Model model){
        List<Notification> notifications = notificationService.getAllNotification();
        model.addAttribute("notifications", notifications);
        model.addAttribute("thereIs", !notifications.isEmpty());
        model.addAttribute("tmp", new Notification());
        notificationService.setAllNotificationOnOld();
        return "user/notification";
    }

    @PostMapping("/notification")
    public String postAllNotificationPage(@ModelAttribute Notification tmp){
        notificationService.delete(tmp);
        return "redirect:/user/notification";
    }
}
