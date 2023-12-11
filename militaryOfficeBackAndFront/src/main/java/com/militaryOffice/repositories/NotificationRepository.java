package com.militaryOffice.repositories;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Notification;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {


}
