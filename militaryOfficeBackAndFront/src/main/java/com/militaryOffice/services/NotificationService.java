package com.militaryOffice.services;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Notification;
import com.militaryOffice.model.NotificationStatus;
import com.militaryOffice.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final CitizenService citizenService;
    private final EmployeeService employeeService;

    public List<Notification> getAllNotification(){
        Citizen citizen = citizenService.getCitizenByAuthentication();
        List<Notification> notifications = notificationRepository.findAll();
        List<Notification> resultList = new ArrayList<>();
        System.out.println(citizen.getId());
        for(Notification notification : notifications)
            if (notification.getUser().getId().equals(citizen.getId()))
                resultList.add(notification);
        return resultList;
    }


    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    public int getCountNewNotification(){
        Citizen citizen = citizenService.getCitizenByAuthentication();
        List<Notification> notifications = notificationRepository.findAll();
        int count = 0;
        for(Notification notification : notifications)
            if (notification.getUser().getId().equals(citizen.getId()) && notification.isNew())
                count++;
        return count;
    }

    public void setAllNotificationOnOld() {
        List<Notification> notifications = getAllNotification();
        for(Notification notification:notifications){
            notification.setNew(false);
            notificationRepository.save(notification);
        }
    }

    public void sendNotification(NotificationStatus notificationStatus, Citizen idUser) {
        Notification notification = new Notification();
        notification.setNew(true);
        notification.setUser(idUser);
        notification.setDate(LocalDate.now());
        notification.setType(notificationStatus);
        notification.setEmployee(employeeService.getEmployeeByAuthentication());
        notificationRepository.save(notification);


    }
}
