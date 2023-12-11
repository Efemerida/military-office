package com.militaryOffice.services;

import com.militaryOffice.model.Citizen;
import com.militaryOffice.model.Department;
import com.militaryOffice.model.Employee;
import com.militaryOffice.model.Role;
import com.militaryOffice.repositories.CitizenRepository;
import com.militaryOffice.repositories.EmployeeRepository;
import com.militaryOffice.security.AccountDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {

    private final CitizenRepository citizenRepository;
    private final EmployeeRepository employeeRepository;

    public Role getRoles(String passport){
        Employee employee = employeeRepository.findByPassport(passport);
        if(employee==null){
            System.out.println(passport);
            Citizen citizen = citizenRepository.findByPassport(passport);
            if(citizen==null) throw new IllegalArgumentException();
            return Role.ROLE_USER;
        }
        System.out.println(employee.getDepartment());
        if(employee.getDepartment().equals(Department.MEDICAL)) return Role.ROLE_DOCTOR;
        else if(employee.getDepartment().equals(Department.SUMMONED)) return Role.ROLE_ADMIN;
        throw new IllegalArgumentException();
    }

    public String getRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails)authentication.getPrincipal();
        return  accountDetails.getAuthorities().toArray()[0].toString();
    }
}
